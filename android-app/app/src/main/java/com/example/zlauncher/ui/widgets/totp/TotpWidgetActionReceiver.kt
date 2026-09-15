package com.example.zlauncher.ui.widgets.totp

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import com.example.zlauncher.core.di.HiltBroadcastReceiver
import com.example.zlauncher.data.otp.OtpWidgetRenderer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 「押された」「アラームが鳴った」を受ける口。
 *
 * **ウィジェットの受け口（[TotpWidgetProvider]）とは別にしてある。** あちらは
 * `exported="true"` が必須で、同じところで受けると他アプリからも「表示」を投げられる
 * ことになる（読み取れはしないが、こちらの意図と無関係にホーム画面へコードが出る）。
 * こちらは `exported="false"`。PendingIntent はこのアプリの権限で送られるので、
 * ホーム画面のタップもアラームもそのまま届く。
 *
 * 保管庫の復号はファイル読み込みを伴うので [goAsync] で IO へ逃がす。ブロードキャストの
 * 本体で読むと遅い端末では ANR の対象になる。
 */
@AndroidEntryPoint
class TotpWidgetActionReceiver : HiltBroadcastReceiver() {

    @Inject
    lateinit var renderer: OtpWidgetRenderer

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onReceive(context: Context, intent: Intent) {
        // Hilt の注入はここでしか起きない。最初に通す（[HiltBroadcastReceiver] 参照）
        super.onReceive(context, intent)

        val widgetId = intent.getIntExtra(
            AppWidgetManager.EXTRA_APPWIDGET_ID,
            AppWidgetManager.INVALID_APPWIDGET_ID,
        )
        if (widgetId == AppWidgetManager.INVALID_APPWIDGET_ID) return

        val work: suspend () -> Unit = when (intent.action) {
            OtpWidgetRenderer.ACTION_TOGGLE -> ({ renderer.toggle(widgetId) })
            OtpWidgetRenderer.ACTION_TICK -> ({ renderer.render(widgetId) })
            else -> return
        }

        val pending = goAsync()
        scope.launch {
            try {
                work()
            } finally {
                pending.finish()
            }
        }
    }
}
