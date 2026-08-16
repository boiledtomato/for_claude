package com.impasto.launcher.data

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

data class AppEntry(
    val packageName: String,
    val activityName: String,
    val label: String,
    /** Stable per-app value, so the painter's noise is the same on every run. */
    val seed: Int,
    /** Cache key — bumping with the app's version repaints on update, nothing else does. */
    val cacheKey: String,
) {
    val component: ComponentName get() = ComponentName(packageName, activityName)

    fun launchIntent(): Intent = Intent(Intent.ACTION_MAIN).apply {
        addCategory(Intent.CATEGORY_LAUNCHER)
        component = this@AppEntry.component
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
    }
}

class AppRepository(private val ctx: Context) {

    suspend fun installedApps(): List<AppEntry> = withContext(Dispatchers.IO) {
        val pm = ctx.packageManager
        val query = Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER)
        pm.queryIntentActivities(query, 0)
            .asSequence()
            .filter { it.activityInfo.packageName != ctx.packageName }   // don't list ourselves
            .map { info ->
                val pkg = info.activityInfo.packageName
                val label = info.loadLabel(pm).toString()
                AppEntry(
                    packageName = pkg,
                    activityName = info.activityInfo.name,
                    label = label,
                    seed = pkg.hashCode() and 0x7FFFFFFF,
                    cacheKey = "$pkg@${versionOf(pm, pkg)}",
                )
            }
            .distinctBy { it.packageName }
            .sortedBy { it.label.lowercase() }
            .toList()
    }

    fun icon(entry: AppEntry): Drawable? = runCatching {
        ctx.packageManager.getActivityIcon(entry.component)
    }.getOrElse {
        runCatching { ctx.packageManager.getApplicationIcon(entry.packageName) }.getOrNull()
    }

    private fun versionOf(pm: PackageManager, pkg: String): Long = runCatching {
        val info = pm.getPackageInfo(pkg, 0)
        if (android.os.Build.VERSION.SDK_INT >= 28) info.longVersionCode
        else @Suppress("DEPRECATION") info.versionCode.toLong()
    }.getOrDefault(0L)
}
