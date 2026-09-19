package com.example.zlauncher.data.widgets

import android.appwidget.AppWidgetHost
import android.appwidget.AppWidgetHostView
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProviderInfo
import android.content.ComponentName
import android.content.Context
import android.os.Process
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * [AppWidgetHost] の管理。
 *
 * 押さえどころ:
 * - `startListening` / `stopListening` を Activity の onStart / onStop に合わせる。
 *   これを怠るとウィジェットが更新されない。
 * - `BIND_APPWIDGET` 権限は署名 / 特権アプリ専用で一般アプリには付与されない。
 *   `bindAppWidgetIdIfAllowed` が false を返したら ACTION_APPWIDGET_BIND でユーザー同意を取る。
 * - 削除時に `deleteAppWidgetId` を呼ばないと ID がリークする。
 */
@Singleton
class WidgetHostController @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    private val appWidgetManager: AppWidgetManager? = runCatching { AppWidgetManager.getInstance(context) }.getOrNull()
    private val host = AppWidgetHost(context, HOST_ID)

    fun startListening() {
        runCatching { host.startListening() }.onFailure { Log.w(TAG, "startListening failed", it) }
    }

    fun stopListening() {
        runCatching { host.stopListening() }.onFailure { Log.w(TAG, "stopListening failed", it) }
    }

    fun allocateAppWidgetId(): Int = host.allocateAppWidgetId()

    fun deleteAppWidgetId(appWidgetId: Int) {
        runCatching { host.deleteAppWidgetId(appWidgetId) }.onFailure { Log.w(TAG, "deleteAppWidgetId failed", it) }
    }

    fun providerInfo(appWidgetId: Int): AppWidgetProviderInfo? =
        runCatching { appWidgetManager?.getAppWidgetInfo(appWidgetId) }.getOrNull()

    /** 現在のユーザーのプロバイダのみ。仕事用プロファイルのウィジェットは MVP では扱わない */
    fun installedProviders(): List<AppWidgetProviderInfo> =
        runCatching { appWidgetManager?.getInstalledProvidersForProfile(Process.myUserHandle()).orEmpty() }
            .onFailure { Log.w(TAG, "installedProviders failed", it) }
            .getOrDefault(emptyList())

    fun bindIfAllowed(appWidgetId: Int, provider: ComponentName): Boolean =
        runCatching { appWidgetManager?.bindAppWidgetIdIfAllowed(appWidgetId, provider) == true }
            .getOrDefault(false)

    fun createView(activityContext: Context, appWidgetId: Int, info: AppWidgetProviderInfo): AppWidgetHostView =
        host.createView(activityContext, appWidgetId, info)

    private companion object {
        const val TAG = "WidgetHostController"
        /** ホスト ID はアプリ内で一意なら何でもよいが、変更すると既存のウィジェットが失われる */
        const val HOST_ID = 0x5A4C
    }
}
