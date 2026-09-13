package com.botanical.launcher.garden

import org.json.JSONObject

/** 切り替えられる版面の一覧。assets/plates/index.json が中身。 */
data class PlateCatalog(
    val plates: List<PlateInfo>,
    val defaultId: String,
) {
    fun has(id: String?): Boolean = id != null && plates.any { it.id == id }

    /** 保存されている選択が消えていても落ちないように解決する。 */
    fun resolve(id: String?): String =
        if (has(id)) id!! else plates.firstOrNull()?.id ?: defaultId

    companion object {
        val Empty = PlateCatalog(emptyList(), "")
    }
}

data class PlateInfo(val id: String, val title: String, val latin: String)

fun parseCatalog(json: String): PlateCatalog {
    val root = JSONObject(json)
    val arr = root.getJSONArray("plates")
    val plates = (0 until arr.length()).map { i ->
        val o = arr.getJSONObject(i)
        PlateInfo(
            id = o.getString("id"),
            title = o.optString("title", o.getString("id")),
            latin = o.optString("latin", ""),
        )
    }
    return PlateCatalog(plates, root.optString("default", plates.firstOrNull()?.id ?: ""))
}
