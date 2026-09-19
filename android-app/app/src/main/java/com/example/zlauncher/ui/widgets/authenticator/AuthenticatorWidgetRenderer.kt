package com.example.zlauncher.ui.widgets.authenticator

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.RemoteViews
import androidx.compose.ui.graphics.toArgb
import com.example.zlauncher.R
import com.example.zlauncher.core.designsystem.ZColorScheme
import com.example.zlauncher.core.designsystem.ZDarkColors
import com.example.zlauncher.core.designsystem.ZLightColors
import com.example.zlauncher.core.designsystem.adjusted
import com.example.zlauncher.data.prefs.LauncherPreferencesRepository
import com.example.zlauncher.domain.model.ThemeMode
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Microsoft Authenticator を開くウィジェットを描く。
 *
 * **中身のコードは出せない。** 他アプリの共有鍵は読めないし（書き出しの口も API も無い）、
 * 他アプリの画面を埋め込む方法も Android には無い。ここで作れるのは「1 タップで開く口」
 * までで、それ以上に見せかけないほうがいい。
 *
 * 入っていなければその旨を出し、押しても何も起きないようにする ― 押して無反応より、
 * 理由が出ているほうがいい。
 */
@Singleton
class AuthenticatorWidgetRenderer @Inject constructor(
    @ApplicationContext private val context: Context,
    private val preferences: LauncherPreferencesRepository,
) {
    suspend fun renderAll() {
        val manager = AppWidgetManager.getInstance(context) ?: return
        val ids = manager.getAppWidgetIds(ComponentName(context, AuthenticatorWidgetProvider::class.java))
        if (ids.isEmpty()) return
        render(manager, ids)
    }

    suspend fun render(manager: AppWidgetManager, widgetIds: IntArray) {
        val colors = colors()
        val installed = launchIntent() != null
        widgetIds.forEach { id -> manager.updateAppWidget(id, views(colors, installed)) }
    }

    private fun views(colors: ZColorScheme, installed: Boolean): RemoteViews {
        val views = RemoteViews(context.packageName, R.layout.widget_authenticator)

        views.setInt(R.id.widget_auth_background, "setColorFilter", colors.surface.toArgb())
        views.setInt(R.id.widget_auth_border, "setColorFilter", colors.outline.toArgb())
        views.setTextColor(R.id.widget_auth_label, colors.textPrimary.toArgb())
        views.setTextColor(
            R.id.widget_auth_hint,
            if (installed) colors.textDim.toArgb() else colors.statusAmber.toArgb(),
        )

        views.setTextViewText(R.id.widget_auth_label, label())
        views.setTextViewText(R.id.widget_auth_hint, if (installed) "Tap to open" else "Not installed")

        icon()?.let { views.setImageViewBitmap(R.id.widget_auth_icon, it) }

        val intent = launchIntent()
        if (intent != null) {
            views.setOnClickPendingIntent(
                R.id.widget_auth_root,
                PendingIntent.getActivity(
                    context,
                    REQUEST_CODE,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
                ),
            )
        }
        return views
    }

    /** 入っていれば相手のアプリ名をそのまま使う。地域化された名前で出るように */
    private fun label(): String = runCatching {
        val info = context.packageManager.getApplicationInfo(PACKAGE, 0)
        context.packageManager.getApplicationLabel(info).toString()
    }.getOrDefault(FALLBACK_LABEL)

    private fun launchIntent() = runCatching {
        context.packageManager.getLaunchIntentForPackage(PACKAGE)
    }.getOrNull()

    /**
     * アプリのアイコン。アダプティブアイコンは [BitmapDrawable] ではないので、
     * RemoteViews に渡せる形へ自分で描き写す。
     */
    private fun icon(): Bitmap? {
        val drawable: Drawable = runCatching {
            context.packageManager.getApplicationIcon(PACKAGE)
        }.getOrNull() ?: return null

        (drawable as? BitmapDrawable)?.bitmap?.let { return it }

        val size = ICON_PX
        val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        drawable.setBounds(0, 0, size, size)
        drawable.draw(Canvas(bitmap))
        return bitmap
    }

    /** アプリ側で明暗を固定できるので、`-night` リソースには任せられない */
    private suspend fun colors(): ZColorScheme {
        val state = runCatching { preferences.state.first() }.getOrNull()
        val dark = when (state?.themeMode ?: ThemeMode.SYSTEM) {
            ThemeMode.DARK -> true
            ThemeMode.LIGHT -> false
            ThemeMode.SYSTEM -> systemDark()
        }
        val base = if (dark) ZDarkColors else ZLightColors
        val adjust = state?.colorAdjust ?: return base
        return base.adjusted(adjust)
    }

    private fun systemDark(): Boolean {
        val mask = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return mask == Configuration.UI_MODE_NIGHT_YES
    }

    private companion object {
        /** Microsoft Authenticator のパッケージ名。マニフェストの queries にも書いてある */
        const val PACKAGE = "com.azure.authenticator"
        const val FALLBACK_LABEL = "Microsoft Authenticator"
        const val REQUEST_CODE = 1
        const val ICON_PX = 144
    }
}
