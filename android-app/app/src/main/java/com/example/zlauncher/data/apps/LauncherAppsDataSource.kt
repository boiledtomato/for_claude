package com.example.zlauncher.data.apps

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.LauncherActivityInfo
import android.content.pm.LauncherApps
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.os.Process
import android.os.UserHandle
import android.os.UserManager
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
 * PackageManager.queryIntentActivities ではなくこちらを使うのは、仕事用プロファイルを
 * 1 行の変更で扱えるため。例外は握りつぶして空リストに落とす（ホームアプリが落ちる方が致命的）。
 */
@Singleton
class LauncherAppsDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    private val launcherApps: LauncherApps? = context.getSystemService()
    private val userManager: UserManager? = context.getSystemService()

    val currentUser: UserHandle get() = Process.myUserHandle()

    /**
     * 個人用 + 仕事用プロファイル。
     * 仕事用プロファイルのアプリが実際に見えるのは既定のホームアプリのときだけなので、
     * 取得できなければ現在のユーザーだけに落とす。
     */
    val profiles: List<UserHandle>
        get() = runCatching { userManager?.userProfiles?.takeIf { it.isNotEmpty() } ?: listOf(currentUser) }
            .onFailure { Log.w(TAG, "userProfiles failed", it) }
            .getOrDefault(listOf(currentUser))

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

    fun activityList(user: UserHandle = currentUser): List<LauncherActivityInfo> =
        runCatching { launcherApps?.getActivityList(null, user).orEmpty() }
            .onFailure { Log.w(TAG, "getActivityList failed for $user", it) }
            .getOrDefault(emptyList())

    /** アイコン取得用。プロファイルをまたぐと PackageManager からは引けないのでこちらを使う */
    fun activityInfo(componentName: ComponentName, user: UserHandle): LauncherActivityInfo? =
        runCatching {
            launcherApps?.getActivityList(componentName.packageName, user)
                ?.firstOrNull { it.componentName == componentName }
        }.getOrNull()

    /**
     * アプリの起動。Intent を自前で組まず [LauncherApps.startMainActivity] を通すのは、
     * プロファイルをまたぐ起動とタップ位置からのズーム演出（sourceBounds）を素直に扱えるため。
     */
    fun launch(
        componentName: ComponentName,
        user: UserHandle,
        sourceBounds: android.graphics.Rect? = null,
        options: android.os.Bundle? = null,
    ): Boolean = runCatching {
        launcherApps?.startMainActivity(componentName, user, sourceBounds, options)
        true
    }.onFailure { Log.w(TAG, "startMainActivity failed for $componentName", it) }.getOrDefault(false)

    fun openAppDetails(
        componentName: ComponentName,
        user: UserHandle,
        sourceBounds: android.graphics.Rect? = null,
    ): Boolean = runCatching {
        launcherApps?.startAppDetailsActivity(componentName, user, sourceBounds, null)
        true
    }.onFailure { Log.w(TAG, "startAppDetailsActivity failed", it) }.getOrDefault(false)

    /**
     * アンインストールの確認画面を出す。**消すのは OS で、こちらは頼むだけ。**
     *
     * `LauncherApps` に削除の API は無い（`DELETE_PACKAGES` は署名権限）ので、
     * システムのアンインストーラを `ACTION_DELETE` で開く。確認ダイアログはその画面が出すため、
     * 呼ぶ前にこちらで二重に聞かない。
     *
     * **個人用プロファイルのアプリ専用。** この Intent が運べるのはパッケージ名だけで、
     * どのユーザーのものかを指定する手段が無い。仕事用プロファイルのアプリに対して投げると、
     * 同じパッケージの個人用のほうが消えうる ― 呼び出し側で弾くこと。
     */
    fun requestUninstall(packageName: String): Boolean = runCatching {
        val intent = Intent(Intent.ACTION_DELETE, Uri.fromParts("package", packageName, null))
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
        true
    }.onFailure { Log.w(TAG, "uninstall request failed for $packageName", it) }.getOrDefault(false)

    private companion object {
        const val TAG = "LauncherAppsDataSource"
    }
}
