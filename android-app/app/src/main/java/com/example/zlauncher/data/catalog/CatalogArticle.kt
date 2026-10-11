package com.example.zlauncher.data.catalog

/**
 * ヘルプ記事の応答から CSV への相対パスを取り出す。
 *
 * **応答は JSON なので、本文の HTML は素のままでは入っていない。** Drupal は本文を
 * JSON 文字列として返すため、リンクは
 * `href="\/downloads\/zia\/…\/Zscaler-Test-URL-Categories-05-21-2026.csv"`
 * の形で届く。`/` は `\/`、`"` は `"` にエスケープされている。
 * そのため `/downloads/…` をそのまま探すと**必ず外れる**（実機で
 * 「The help article no longer links a CSV」になっていたのがこれ）。
 *
 * 先にエスケープを戻してから探す。JSON をパースしないのは、本文がどのキーに入るかが
 * `view_type` や記事の種類で変わるためで、リンク 1 本を拾うのに構造へ依存したくない。
 */
internal object CatalogArticle {

    private val UNICODE_ESCAPE = Regex("""\\u([0-9a-fA-F]{4})""")

    /** 本文に埋まっている CSV への相対リンク。エスケープを戻した後の形で当てる */
    private val CSV_LINK = Regex("""/downloads/[^"'\\\s<>]+\.csv""", RegexOption.IGNORE_CASE)

    fun csvPath(body: String): String? = CSV_LINK.find(unescape(body))?.value

    /**
     * JSON 文字列エスケープを戻す。拾いたいのはパス 1 本だけなので、
     * リンクの周りに出るものだけを扱う（`\uXXXX`・`\/`・`\"`・`\\`）。
     */
    private fun unescape(body: String): String {
        val decoded = UNICODE_ESCAPE.replace(body) { match ->
            match.groupValues[1].toInt(16).toChar().toString()
        }
        return decoded.replace("\\/", "/").replace("\\\"", "\"")
    }
}
