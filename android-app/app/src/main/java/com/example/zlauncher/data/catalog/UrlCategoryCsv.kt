package com.example.zlauncher.data.catalog

import com.example.zlauncher.domain.model.UrlCategoryEntry

/**
 * Zscaler ヘルプの URL カテゴリー CSV を読む。
 *
 * 実ファイルの癖に合わせてある:
 * - 先頭に BOM が付く
 * - ヘッダーの "Category " のように列名の末尾に空白が入る
 * - 説明文にカンマが入るので、引用符の処理が必須（素朴な split では壊れる）
 * - Class 列に注記文だけを入れた行が混ざる（他の列が空）。これはデータではないので落とす
 */
object UrlCategoryCsv {

    /** BOM はリテラルで書かない。ソース中に生の U+FEFF があるとツールによって扱いが割れる */
    private const val BOM = "\uFEFF"

    /**
     * ATP（Advanced Threat Protection）専用のカテゴリー。
     *
     * CSV 自身が「Advanced Security 大項目のカテゴリーは ATP ポリシーにのみ適用される」と
     * 注記している。閲覧内容の分類ではなく脅威検知の区分なので、ランチャーのフォルダとしては
     * 意味を成さない。取り込み時点で落とす。
     */
    private const val ATP_SUPER_CATEGORY = "Advanced Security"
    private const val ATP_CLASS = "Advanced Security Risk"

    /**
     * SSL Inspection ポリシー専用のカテゴリー。
     *
     * ATP と同じ理由で落とす。CSV 自身が「Microsoft Office 365 大項目のカテゴリーは
     * SSL Inspection ポリシーにのみ適用される」と注記しているとおり、閲覧内容の分類ではなく
     * 検査対象の指定であり、ランチャーのフォルダとしては意味を成さない。
     */
    private const val SSL_INSPECTION_SUPER_CATEGORY = "Microsoft Office 365"

    fun parse(text: String): List<UrlCategoryEntry> {
        val rows = splitRows(text.removePrefix(BOM))
        if (rows.isEmpty()) return emptyList()

        val header = rows.first().map { it.trim().lowercase() }
        fun indexOf(vararg names: String): Int =
            names.firstNotNullOfOrNull { name ->
                header.indexOf(name).takeIf { it >= 0 }
            } ?: -1

        val classIndex = indexOf("class")
        val superIndex = indexOf("super-category", "super category")
        val categoryIndex = indexOf("category")
        val descriptionIndex = indexOf("description")
        if (superIndex < 0 || categoryIndex < 0) return emptyList()

        return rows.drop(1).mapNotNull { row ->
            fun cell(index: Int): String = if (index in row.indices) row[index].trim() else ""
            val superCategory = cell(superIndex)
            val category = cell(categoryIndex)
            // 注記行は Class にだけ文章が入り、他が空になる
            if (superCategory.isEmpty() || category.isEmpty()) return@mapNotNull null

            val className = cell(classIndex)
            if (superCategory.equals(ATP_SUPER_CATEGORY, ignoreCase = true)) return@mapNotNull null
            if (className.equals(ATP_CLASS, ignoreCase = true)) return@mapNotNull null
            if (superCategory.equals(SSL_INSPECTION_SUPER_CATEGORY, ignoreCase = true)) return@mapNotNull null

            UrlCategoryEntry(
                superCategory = superCategory,
                category = category,
                description = cell(descriptionIndex),
                className = className,
            )
        }
    }

    /** RFC 4180 の最小限。引用符の中では改行とカンマをそのまま通す */
    private fun splitRows(text: String): List<List<String>> {
        val rows = mutableListOf<List<String>>()
        var row = mutableListOf<String>()
        val field = StringBuilder()
        var quoted = false
        var index = 0

        fun endField() {
            row.add(field.toString())
            field.setLength(0)
        }

        fun endRow() {
            endField()
            if (row.any { it.isNotBlank() }) rows.add(row)
            row = mutableListOf()
        }

        while (index < text.length) {
            val char = text[index]
            when {
                quoted && char == '"' && index + 1 < text.length && text[index + 1] == '"' -> {
                    field.append('"')
                    index++
                }
                char == '"' -> quoted = !quoted
                !quoted && char == ',' -> endField()
                !quoted && (char == '\n' || char == '\r') -> {
                    // CRLF を 2 行と数えないよう、\r\n はまとめて 1 区切りにする
                    if (char == '\r' && index + 1 < text.length && text[index + 1] == '\n') index++
                    endRow()
                }
                else -> field.append(char)
            }
            index++
        }
        if (field.isNotEmpty() || row.isNotEmpty()) endRow()
        return rows
    }
}
