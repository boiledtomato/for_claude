package com.example.zlauncher.ui.console

import android.content.Context
import android.content.Intent
import com.example.zlauncher.MainActivity

/**
 * コンソールの特定の面を開いて起動する。
 *
 * ランチャー本体の外（ウィジェットの設定画面など）から「ここで登録してください」と
 * 案内する先が要る。面の選択は [ConsoleViewModel] の中にあるので、Intent の付加情報で
 * 渡し、[MainActivity] が流し込む。
 */
/**
 * 1 回ぶんの依頼。[id] は「もう処理した依頼か」を見分けるためだけに付いている ―
 * flow を replay ありで持っているので、これが無いとドロワーから戻るたびに同じ面へ
 * 飛ばされる。
 */
data class PaneRequest(val pane: String, val id: Long)

object ConsoleDeepLink {
    const val EXTRA_PANE = "com.example.zlauncher.extra.PANE"
    const val PANE_AUTH = "auth"

    fun intent(context: Context, pane: String): Intent =
        Intent(context, MainActivity::class.java)
            .putExtra(EXTRA_PANE, pane)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
}
