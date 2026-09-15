package com.example.zlauncher.ui.widgets.totp

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import com.example.zlauncher.data.otp.OtpWidgetRenderer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ホーム画面に置いた認証コード 1 件ぶんの受け口。
 *
 * ここに来るのはシステムからの出し入れ（追加・削除・復帰）だけ。タップとアラームは
 * [TotpWidgetActionReceiver]（`exported="false"`）が受ける ― ウィジェットの提供元は
 * 公開が必須なので、同じところで受けると他アプリからコードを出させられる。
 *
 * 描くのは [OtpWidgetRenderer]。保管庫の復号はファイル読み込みを伴うので [goAsync] で
 * IO に逃がす。ブロードキャストの本体で読むと、遅い端末では ANR の対象になる。
 */
@AndroidEntryPoint
class TotpWidgetProvider : AppWidgetProvider() {

    @Inject
    lateinit var renderer: OtpWidgetRenderer

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray,
    ) {
        // 置かれた直後と端末の復帰時。コードは出さない（伏せた状態で描く）
        val pending = goAsync()
        scope.launch {
            try {
                appWidgetIds.forEach { renderer.render(it) }
            } finally {
                pending.finish()
            }
        }
    }

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        // 割り当てを残すと、同じ id が別のウィジェットに配り直されたときに
        // 前の登録が映る。アラームもここで止める
        renderer.forget(appWidgetIds)
    }

}
