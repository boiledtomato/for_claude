package com.example.zlauncher.data.apps

import android.content.Context
import android.content.pm.LauncherApps
import android.os.Handler
import android.os.Looper
import android.os.Process
import android.os.UserHandle
import android.util.Log
import androidx.core.content.getSystemService
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate
import javax.inject.Inject
import javax.inject.Singleton

/**
 * [LauncherApps] の薄いラッパ。
 *
 * PackageManager.queryIntentActivities ではなくこちらを使うのは、
 * マルチユーザー / Work Profile へ広げるときに実装が変わらないため。
 * 例外は握りつぶして空リストに落とす（ホームアプリが落ちる方が致命的）。
 */
@Singleton
class LauncherAppsDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    private val launcherApps: LauncherApps? = context.getSystemService()

    val currentUser: UserHandle get() = Process.myUserHandle()

    /** 初回に 1 回、その後はインストール / 削除 / 更新のたびに emit する */
    val changes: Flow<Unit> = callbackFlow {
        trySend(Unit)
        val apps = launcherApps
        if (apps == null) {
            awaitClose { }
            return@callbackFlow
        }
        val callback = object : LauncherApps.Callback() {
            override fun onPackageRemoved(packageName: String?, user: UserHandle?) { trySend(Unit) }
            override fun onPackageAdded(packageName: String?, user: UserHandle?) { trySend(Unit) }
            override fun onPackageChanged(packageName: String?, user: UserHandle?) { trySend(Unit) }
            override fun onPackagesAvailable(names: Array<out String>?, user: UserHandle?, replacing: Boolean) { trySend(Unit) }
            override fun onPackagesUnavailable(names: Array<out String>?, user: UserHandle?, replacing: Boolean) { trySend(Unit) }
        }
        runCatching { apps.registerCallback(callback, Handler(Looper.getMainLooper())) }
            .onFailure { Log.w(TAG, "registerCallback failed", it) }
        awaitClose { runCatching { apps.unregisterCallback(callback) } }
    }.conflate()

    fun activityList(user: UserHandle = currentUser): List<android.content.pm.LauncherActivityInfo> =
        runCatching { launcherApps?.getActivityList(null, user).orEmpty() }
            .onFailure { Log.w(TAG, "getActivityList failed", it) }
            .getOrDefault(emptyList())

    /**
     * アプリの起動。Intent を自前で組まず [LauncherApps.startMainActivity] を通すのは、
     * マルチユーザーとタップ位置からのズーム演出（sourceBounds）を素直に扱えるため。
     */
    fun launch(
        componentName: android.content.ComponentName,
        user: UserHandle,
        sourceBounds: android.graphics.Rect? = null,
        options: android.os.Bundle? = null,
    ): Boolean = runCatching {
        launcherApps?.startMainActivity(componentName, user, sourceBounds, options)
        true
    }.onFailure { Log.w(TAG, "startMainActivity failed for $componentName", it) }.getOrDefault(false)

    fun openAppDetails(
        componentName: android.content.ComponentName,
        user: UserHandle,
        sourceBounds: android.graphics.Rect? = null,
    ): Boolean = runCatching {
        launcherApps?.startAppDetailsActivity(componentName, user, sourceBounds, null)
        true
    }.onFailure { Log.w(TAG, "startAppDetailsActivity failed", it) }.getOrDefault(false)

    private companion object {
        const val TAG = "LauncherAppsDataSource"
    }
}
