package com.example.critterhome.data

import android.content.Context
import android.content.pm.LauncherApps
import android.graphics.Bitmap
import android.graphics.Rect
import android.os.Handler
import android.os.Looper
import android.os.Process
import android.os.UserHandle
import android.os.UserManager
import android.util.Log
import android.util.LruCache
import androidx.core.content.getSystemService
import androidx.core.graphics.drawable.toBitmap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.withContext

/**
 * インストール済みアプリ。[LauncherApps] を通すのは、仕事用プロファイルとタップ位置からの
 * 起動演出を素直に扱えるため。
 *
 * **例外は握りつぶして空に落とす。** ホームアプリが落ちると端末が操作できなくなる。
 */
class AppsRepository(private val context: Context, scope: CoroutineScope) {

    private val launcherApps: LauncherApps? = context.getSystemService()
    private val userManager: UserManager? = context.getSystemService()
    private val icons = object : LruCache<String, Bitmap>(ICON_CACHE_BYTES) {
        override fun sizeOf(key: String, value: Bitmap): Int = value.byteCount
    }

    /** 初回と、インストール / 削除 / 更新のたびに読み直す */
    val apps: StateFlow<List<AppEntry>> = callbackFlow {
        trySend(Unit)
        val apps = launcherApps
        val callback = object : LauncherApps.Callback() {
            override fun onPackageRemoved(packageName: String?, user: UserHandle?) { trySend(Unit) }
            override fun onPackageAdded(packageName: String?, user: UserHandle?) { trySend(Unit) }
            override fun onPackageChanged(packageName: String?, user: UserHandle?) { trySend(Unit) }
            override fun onPackagesAvailable(names: Array<out String>?, user: UserHandle?, replacing: Boolean) { trySend(Unit) }
            override fun onPackagesUnavailable(names: Array<out String>?, user: UserHandle?, replacing: Boolean) { trySend(Unit) }
        }
        runCatching { apps?.registerCallback(callback, Handler(Looper.getMainLooper())) }
            .onFailure { Log.w(TAG, "registerCallback failed", it) }
        awaitClose { runCatching { apps?.unregisterCallback(callback) } }
    }
        .conflate()
        .map { load() }
        .flowOn(Dispatchers.IO)
        .stateIn(scope, SharingStarted.Eagerly, emptyList())

    private fun load(): List<AppEntry> {
        val me = Process.myUserHandle()
        val profiles = runCatching { userManager?.userProfiles?.takeIf { it.isNotEmpty() } }
            .getOrNull() ?: listOf(me)
        return profiles.flatMap { user ->
            runCatching { launcherApps?.getActivityList(null, user).orEmpty() }
                .onFailure { Log.w(TAG, "getActivityList failed for $user", it) }
                .getOrDefault(emptyList())
                // 自分自身はアプリ一覧に出さない
                .filter { it.componentName.packageName != context.packageName }
                .map { info ->
                    AppEntry(
                        packageName = info.componentName.packageName,
                        componentName = info.componentName,
                        label = info.label?.toString().orEmpty().ifBlank { info.componentName.packageName },
                        user = user,
                        isWorkProfile = user != me,
                        declaredCategory = info.applicationInfo.category,
                    )
                }
        }.sortedBy { it.label.lowercase() }
    }

    /** 仕事用には OS が鞄の印を重ねたアイコンを返す（getBadgedIcon） */
    suspend fun icon(entry: AppEntry): Bitmap? {
        icons.get(entry.key)?.let { return it }
        return withContext(Dispatchers.IO) {
            runCatching {
                val info = launcherApps?.getActivityList(entry.packageName, entry.user)
                    ?.firstOrNull { it.componentName == entry.componentName } ?: return@runCatching null
                info.getBadgedIcon(0).toBitmap(ICON_PX, ICON_PX)
            }.getOrNull()?.also { icons.put(entry.key, it) }
        }
    }

    fun launch(entry: AppEntry, bounds: Rect? = null): Boolean = runCatching {
        launcherApps?.startMainActivity(entry.componentName, entry.user, bounds, null) ?: return false
        true
    }.onFailure { Log.w(TAG, "launch failed for ${entry.componentName}", it) }.getOrDefault(false)

    fun openAppInfo(entry: AppEntry): Boolean = runCatching {
        launcherApps?.startAppDetailsActivity(entry.componentName, entry.user, null, null) ?: return false
        true
    }.onFailure { Log.w(TAG, "app info failed", it) }.getOrDefault(false)

    private companion object {
        const val TAG = "AppsRepository"
        const val ICON_PX = 128
        const val ICON_CACHE_BYTES = 16 * 1024 * 1024
    }
}
