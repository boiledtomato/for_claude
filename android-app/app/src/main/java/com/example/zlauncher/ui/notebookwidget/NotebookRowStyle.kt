package com.example.zlauncher.ui.notebookwidget

import android.content.Context
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.TypefaceSpan
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.example.zlauncher.R
import com.example.zlauncher.core.notebook.NotebookDivider
import com.example.zlauncher.core.notebook.NotebookFont
import com.example.zlauncher.core.notebook.NotebookIcons
import com.example.zlauncher.core.notebook.NotebookKind
import com.example.zlauncher.core.notebook.NotebookTextColor
import com.example.zlauncher.core.notebook.NotebookWidgetStyle

/**
 * 選んだ見た目を `RemoteViews` に当てる。
 *
 * **文字色と字体は span で渡す。** `RemoteViews` にはレイアウトを差し替える以外に書体を
 * 変える確実な手段が無い（`TextView.setFontFamily` が遠隔から呼べる保証が無く、
 * フォント指定ごとにレイアウトを増やすと XML が掛け算で増える）。`ForegroundColorSpan` と
 * `TypefaceSpan` はどちらも `ParcelableSpan` なので、別プロセスのランチャーまでそのまま
 * 届く ― 通知の装飾と同じ仕組み。レイアウトは 1 つで済む。
 *
 * **罫線とアイコンはベクターを資源 id で差し替える。** 破線を `stroke` の `dashWidth` で
 * 描くとハードウェア描画で実線になることがあり、ウィジェットでは `layerType` を
 * software に落とせない。破片そのものをベクターで並べた図を用意してあるので、
 * その問題が起きないまま拡大にも耐える。
 */
object NotebookRowStyle {

    /** 配色そのままのときは null を返す。span を付けず、レイアウトの色を使わせる */
    @ColorRes
    fun textColorRes(choice: NotebookTextColor): Int? = when (choice) {
        NotebookTextColor.DEFAULT -> null
        NotebookTextColor.ACCENT -> R.color.widget_text_accent
        NotebookTextColor.WARM -> R.color.widget_text_warm
        NotebookTextColor.COOL -> R.color.widget_text_cool
        NotebookTextColor.DIM -> R.color.widget_text_secondary
    }

    /** 罫線を出さないときは null */
    @DrawableRes
    fun dividerRes(divider: NotebookDivider): Int? = when (divider) {
        NotebookDivider.NONE -> null
        NotebookDivider.SOLID -> R.drawable.divider_solid
        NotebookDivider.DASHED -> R.drawable.divider_dashed
        NotebookDivider.DOTTED -> R.drawable.divider_dotted
    }

    @DrawableRes
    fun iconRes(kind: NotebookKind): Int = when (kind) {
        NotebookKind.LEARN -> R.drawable.ic_notebook_learn
        NotebookKind.SHIELD -> R.drawable.ic_notebook_shield
        NotebookKind.COMMUNITY -> R.drawable.ic_notebook_community
        NotebookKind.UPDATES -> R.drawable.ic_notebook_updates
        NotebookKind.GENERIC -> R.drawable.ic_notebook_generic
    }

    fun iconFor(title: String): Int = iconRes(NotebookIcons.kindFor(title))

    /**
     * 文字色と字体を当てた文字列。
     *
     * 既定のままの項目には span を付けない ― 付けても同じ見た目になるが、
     * 余計な span は別プロセスへ運ぶ荷物が増えるだけ。
     *
     * @param dim 補助行（2 行目）か。既定色のままなら薄い色を使う
     */
    fun styled(
        context: Context,
        text: CharSequence,
        style: NotebookWidgetStyle,
        dim: Boolean = false,
    ): CharSequence {
        val colorRes = textColorRes(style.textColor)
        val needsFont = style.font != NotebookFont.SANS
        if (colorRes == null && !needsFont) return text

        val spannable = SpannableString(text)
        val end = spannable.length
        if (colorRes != null) {
            // 2 行目は 1 行目より控えめにする。同じ濃さだと題が沈む
            val resolved = ContextCompat.getColor(
                context,
                if (dim) R.color.widget_text_secondary else colorRes,
            )
            spannable.setSpan(ForegroundColorSpan(resolved), 0, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        }
        if (needsFont) {
            spannable.setSpan(
                TypefaceSpan(style.font.family),
                0,
                end,
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE,
            )
        }
        return spannable
    }
}
