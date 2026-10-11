package com.example.zlauncher.core.notebook

import kotlinx.serialization.Serializable

/**
 * ウィジェットの見た目。**Android に依存しない**ので、既定値も選択肢もそのまま固定できる。
 *
 * 色そのものではなく「どれを選んだか」を持つ。実際の値は明暗で違い、色資源として
 * 引く必要があるため（`ZColors` は CompositionLocal 由来で `RemoteViews` から読めない）。
 * ここに `#RRGGBB` を保存すると、暗い面に合わせた色が明るい面で読めなくなる。
 */
@Serializable
data class NotebookWidgetStyle(
    val textColor: NotebookTextColor = NotebookTextColor.DEFAULT,
    val font: NotebookFont = NotebookFont.SANS,
    val divider: NotebookDivider = NotebookDivider.SOLID,
    /** 種類ごとのアイコンを出すか。切ると絵文字に戻る */
    val showIcons: Boolean = true,
)

/** 選べる文字色。[DEFAULT] は配色そのまま（span を付けない） */
@Serializable
enum class NotebookTextColor { DEFAULT, ACCENT, WARM, COOL, DIM }

/**
 * 選べる字体。
 *
 * 端末に必ずある総称名だけにしてある ― 独自フォントを同梱しても `RemoteViews` から
 * 指定する確実な方法が無く（`TypefaceSpan(Typeface)` は API 28+ でも別プロセスへ
 * 渡らない）、総称名なら `TypefaceSpan(String)` がそのまま届く。
 */
@Serializable
enum class NotebookFont(val family: String) {
    SANS("sans-serif"),
    CONDENSED("sans-serif-condensed"),
    SERIF("serif"),
    MONO("monospace"),
}

/** 行の下の罫線 */
@Serializable
enum class NotebookDivider { NONE, SOLID, DASHED, DOTTED }

/**
 * ノートブックの種類。題から決める。**純粋なのでそのままテストにかけられる。**
 *
 * 題はパイプラインが付けたもの（`Zscaler_help_docs` など）で、こちらの都合では変えられない。
 * 増えたときに全部 [GENERIC] に落ちるのは許容 ― 間違った絵を出すよりいい。
 */
enum class NotebookKind { LEARN, SHIELD, COMMUNITY, UPDATES, GENERIC }

object NotebookIcons {

    /**
     * 題から種類を決める。**順番が意味を持つ** ― `Zscaler_community` は
     * 「Zscaler」にも「community」にも当たるので、狭いほうを先に見る。
     */
    fun kindFor(title: String): NotebookKind {
        val name = title.lowercase()
        return when {
            // Microsoft Learn の資料
            "mslearn" in name || "learn" in name || "entra" in name -> NotebookKind.LEARN
            // フォーラムの投稿。Zscaler より先に見る
            "community" in name || "zenith" in name -> NotebookKind.COMMUNITY
            // リリースノートのダイジェスト。これも Zscaler より先
            "update" in name || "release" in name -> NotebookKind.UPDATES
            // 残った Zscaler は公式ドキュメント
            "zscaler" in name || "zia" in name || "zpa" in name || "zdx" in name ->
                NotebookKind.SHIELD
            else -> NotebookKind.GENERIC
        }
    }
}
