package com.example.zlauncher.domain.model

/**
 * ログ 1 行に載せるカテゴリーの見た目（id・表示名・色）。
 *
 * 行を書く時点でカテゴリーを引き当ててしまうので、画面側は配るだけで済む。
 */
data class InsightBucket(val id: String, val name: String, val colorIndex: Int)

/**
 * 通信ログをどのカテゴリーに載せるか。**Android に依存しない**ので、そのままテストにかけられる。
 *
 * ここを間違えると「作ったカテゴリーがログに出てこない」という形で出る。実際に出たのが
 * まさにそれで、`Miscellaneous / Unknown`（未分類を集める枠）に入っているアプリの通信が
 * `Uncategorized` という別の名前で記録されていた ― **あの枠の所属は保存されていない**
 * （[CategoryRules.catchAllPackages] がその都度計算する）ので、
 * [AppCategory.packages] から引いた対応表には 1 つも載らず、全部こぼれていたため。
 */
object InsightCategories {

    /**
     * 未分類を集める枠が**無いとき**だけ使う、器としての擬似カテゴリー。
     *
     * 枠があるならそちらが受け皿なので、この名前は出さない。2 つあると、同じ
     * 「分類していないアプリ」が別名で 2 か所に積まれて読めなくなる。
     */
    val UNCATEGORIZED = InsightBucket(UNCATEGORIZED_ID, UNCATEGORIZED_NAME, NO_COLOR_INDEX)

    /**
     * パッケージ名 → そのパッケージを明示的に抱えているカテゴリー。
     *
     * **未分類を集める枠は入れない。** あの枠の所属は導出なので、明示的に抱えることは無い
     * （古い保存データが [AppCategory.packages] を持っていても、ここで無視する）。
     * 1 つのアプリが複数カテゴリーに入っていても、最初に一致した 1 つだけを使う ―
     * でないとバイト数が二重に数えられる。
     */
    fun explicitOwners(categories: List<AppCategory>): Map<String, InsightBucket> {
        val owners = HashMap<String, InsightBucket>()
        categories.filterNot { it.isCatchAll }.forEach { category ->
            val bucket = InsightBucket(category.id, category.name, category.colorIndex)
            category.packages.forEach { pkg -> owners.getOrPut(pkg) { bucket } }
        }
        return owners
    }

    /**
     * どのカテゴリーにも属さない通信の置き場。
     *
     * 未分類を集める枠があればそれを返す ― レールに並んでいる名前と色でログに出る。
     * 無ければ [UNCATEGORIZED]。どちらにしても捨てない: 分類していないアプリが通信の
     * 大半を占めている、という事実こそ見たいものなので。
     */
    fun fallback(categories: List<AppCategory>): InsightBucket =
        CategoryRules.catchAll(categories)
            ?.let { InsightBucket(it.id, it.name, it.colorIndex) }
            ?: UNCATEGORIZED

    /**
     * 一覧の並び。通信量の多い順で、**置き場だけは常に最後**。
     *
     * 置き場は放っておくと一番太くなり、自分で作ったカテゴリーを下へ押し出してしまう。
     * [fallbackId] で判定するので、名前が `Uncategorized` でも
     * `Miscellaneous / Unknown` でも同じように最後に来る。
     */
    fun sort(
        rollups: List<CategoryRollup>,
        categories: List<AppCategory>,
        fallbackId: String,
    ): List<CategoryRollup> {
        val rank = categories.withIndex().associate { (index, category) -> category.id to index }
        return rollups.sortedWith(
            compareBy<CategoryRollup> { it.id == fallbackId }
                .thenByDescending { it.totalBytes }
                .thenBy { rank[it.id] ?: Int.MAX_VALUE }
        )
    }
}
