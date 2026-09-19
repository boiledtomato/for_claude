package com.example.zlauncher.ui.widgets.authenticator

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ホーム画面に置く「Microsoft Authenticator を開く」枠。
 *
 * 描くのは [AuthenticatorWidgetRenderer]。設定を読むのに DataStore を触るので、
 * どの経路も [goAsync] で IO に逃がす ― ブロードキャストの本体で読むと ANR の対象になる。
 */
@AndroidEntryPoint
class AuthenticatorWidgetProvider : AppWidgetProvider() {

    @Inject
    lateinit var renderer: AuthenticatorWidgetRenderer

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray,
    ) {
        val pending = goAsync()
        scope.launch {
            try {
                renderer.render(appWidgetManager, appWidgetIds)
            } finally {
                pending.finish()
            }
        }
    }
}
