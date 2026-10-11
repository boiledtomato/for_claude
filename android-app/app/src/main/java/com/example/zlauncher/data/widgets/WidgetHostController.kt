package com.example.zlauncher.data.widgets

import android.appwidget.AppWidgetHost
import android.appwidget.AppWidgetHostView
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProviderInfo
import android.content.ComponentName
import android.content.Context
import android.os.Process
import android.os.UserHandle
import android.os.UserManager
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
    private val userManager: UserManager? = runCatching { context.getSystemService(UserManager::class.java) }.getOrNull()
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

    /**
     * 個人用と仕事用、**両方のプロファイル**のプロバイダ。
     *
     * `getInstalledProvidersForProfile` は名前のとおり 1 プロファイル分しか返さないので、
     * プロファイルごとに呼んで束ねる。以前は個人用だけを見ており、仕事用にしか入っていない
     * アプリのウィジェットは一覧に出しようがなかった。
     *
     * プロファイルの取得に失敗しても、少なくとも自分の分は返す（端末に仕事用が無いのが
     * 普通なので、ここで空にしてしまうと全滅する）。
     */
    fun installedProviders(): List<AppWidgetProviderInfo> {
        val manager = appWidgetManager ?: return emptyList()
        val profiles = runCatching { userManager?.userProfiles.orEmpty() }
            .onFailure { Log.w(TAG, "userProfiles failed", it) }
            .getOrDefault(emptyList())
            .ifEmpty { listOf(Process.myUserHandle()) }
        return profiles.flatMap { user ->
            runCatching { manager.getInstalledProvidersForProfile(user).orEmpty() }
                .onFailure { Log.w(TAG, "installedProviders failed for $user", it) }
                .getOrDefault(emptyList())
        }
    }

    /**
     * プロバイダが**どのプロファイルのものか**を渡す版を使う。引数の無い版は呼び出し元の
     * ユーザーを前提にするので、仕事用のウィジェットを個人用として結び付けようとして失敗する。
     */
    fun bindIfAllowed(appWidgetId: Int, user: UserHandle, provider: ComponentName): Boolean =
        runCatching { appWidgetManager?.bindAppWidgetIdIfAllowed(appWidgetId, user, provider, null) == true }
            .onFailure { Log.w(TAG, "bindIfAllowed failed for $provider", it) }
            .getOrDefault(false)

    fun createView(activityContext: Context, appWidgetId: Int, info: AppWidgetProviderInfo): AppWidgetHostView =
        host.createView(activityContext, appWidgetId, info)

    private companion object {
        const val TAG = "WidgetHostController"
        /** ホスト ID はアプリ内で一意なら何でもよいが、変更すると既存のウィジェットが失われる */
        const val HOST_ID = 0x5A4C
    }
}
