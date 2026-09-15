package com.example.zlauncher.data.otp

import android.app.AlarmManager
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.SystemClock
import android.widget.RemoteViews
import androidx.compose.ui.graphics.toArgb
import com.example.zlauncher.R
import com.example.zlauncher.core.designsystem.ZColorScheme
import com.example.zlauncher.core.designsystem.ZDarkColors
import com.example.zlauncher.core.designsystem.ZLightColors
import com.example.zlauncher.core.otp.Totp
import com.example.zlauncher.core.otp.TotpReveal
import com.example.zlauncher.data.prefs.LauncherPreferencesRepository
import com.example.zlauncher.domain.model.OtpEntry
import com.example.zlauncher.domain.model.ThemeMode
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

/**
 * ホーム画面のコードを描く。
 *
 * **[OtpRepository] を通さない。** あちらは認証コードの面を開いているあいだ復号した鍵を
 * 抱えておく入れ物で、ウィジェットとは寿命が違う。ここでは描くときだけ [OtpVault] を読み、
 * 読んだ内容は返さずその場で捨てる。
 *
 * 更新の流れはタップ起点。押されたら 30 秒の窓を開け、1 本のアラームで
 * 「コードが切れる時刻」か「窓の終わり」の早いほうに起き、また同じ判断をする
 * （[TotpReveal]）。システムの定期更新（最短 30 分）は 30 秒ごとのコードには使えない。
 */
@Singleton
class OtpWidgetRenderer @Inject constructor(
    @ApplicationContext private val context: Context,
    private val store: OtpWidgetStore,
    private val vault: OtpVault,
    private val preferences: LauncherPreferencesRepository,
) {
    /** 押された。隠れていれば出し、出ていれば隠す */
    suspend fun toggle(widgetId: Int) {
        val now = System.currentTimeMillis()
        val revealed = TotpReveal.isRevealed(now, store.revealUntil(widgetId))
        store.setRevealUntil(widgetId, if (revealed) 0L else TotpReveal.windowEnd(now))
        render(widgetId)
    }

    /** いまの状態で 1 枚描き直す。出ているあいだは次に起きる時刻も置き直す */
    suspend fun render(widgetId: Int) {
        val manager = AppWidgetManager.getInstance(context)
        val colors = colors()
        val binding = store.binding(widgetId)
        val now = System.currentTimeMillis()

        if (binding == null) {
            manager.updateAppWidget(widgetId, unbound(widgetId, colors))
            cancelTick(widgetId)
            return
        }

        val revealed = TotpReveal.isRevealed(now, store.revealUntil(widgetId))
        if (!revealed) {
            store.setRevealUntil(widgetId, 0L)
            cancelTick(widgetId)
            manager.updateAppWidget(widgetId, hidden(widgetId, binding, colors))
            return
        }

        when (val result = vault.load()) {
            OtpVault.Result.Locked -> {
                store.setRevealUntil(widgetId, 0L)
                cancelTick(widgetId)
                manager.updateAppWidget(
                    widgetId,
                    notice(widgetId, binding, colors, "Unlock the phone to show it"),
                )
            }

            OtpVault.Result.Unreadable -> {
                store.setRevealUntil(widgetId, 0L)
                cancelTick(widgetId)
                manager.updateAppWidget(
                    widgetId,
                    notice(widgetId, binding, colors, "Open ZLauncher to repair the vault"),
                )
            }

            is OtpVault.Result.Ready -> {
                val entry = result.entries.firstOrNull { it.id == binding.entryId }
                if (entry == null) {
                    store.setRevealUntil(widgetId, 0L)
                    cancelTick(widgetId)
                    manager.updateAppWidget(
                        widgetId,
                        notice(widgetId, binding, colors, "No longer in the vault"),
                    )
                    return
                }
                // 保管庫のほうで名前や桁数が変わっていることがある。映しているのは同じ登録なので
                // 黙って追従させる（利用者にもう一度ウィジェットを置き直させる理由が無い）。
                // bind() は窓を閉じるので、開いていた期限を控えてから入れ直す
                val until = store.revealUntil(widgetId)
                store.bind(
                    widgetId,
                    OtpWidgetStore.Binding(
                        entryId = entry.id,
                        issuer = entry.issuer,
                        account = entry.account,
                        digits = entry.digits,
                        periodSeconds = entry.periodSeconds,
                    ),
                )
                store.setRevealUntil(widgetId, until)
                manager.updateAppWidget(widgetId, code(widgetId, entry, colors, now))
                scheduleTick(widgetId, TotpReveal.nextTickAt(now, until, entry.periodSeconds))
            }
        }
    }

    /**
     * 保管庫が書き換わったので、置いてあるウィジェットを合わせる。
     *
     * 伏せているあいだは保管庫を読まない作りなので、名前を変えただけだと次にタップされる
     * まで古い名前が出たままになる。書き込み側（[OtpRepository]）から手持ちの一覧を渡して
     * もらい、そのぶんだけ直す ― ここで復号し直す必要は無い。
     */
    suspend fun syncBindings(entries: List<OtpEntry>) {
        val manager = AppWidgetManager.getInstance(context)
        val ids = manager.getAppWidgetIds(ComponentName(context, PROVIDER_CLASS))
        if (ids.isEmpty()) return

        val byId = entries.associateBy { it.id }
        ids.forEach { widgetId ->
            val binding = store.binding(widgetId) ?: return@forEach
            val entry = byId[binding.entryId]
            if (entry != null) {
                val updated = OtpWidgetStore.Binding(
                    entryId = entry.id,
                    issuer = entry.issuer,
                    account = entry.account,
                    digits = entry.digits,
                    periodSeconds = entry.periodSeconds,
                )
                if (updated != binding) {
                    val until = store.revealUntil(widgetId)
                    store.bind(widgetId, updated)
                    store.setRevealUntil(widgetId, until)
                }
            }
            render(widgetId)
        }
    }

    fun forget(widgetIds: IntArray) {
        widgetIds.forEach {
            cancelTick(it)
            store.forget(it)
        }
    }

    // ---- 描画 ---------------------------------------------------------------

    private fun base(widgetId: Int, colors: ZColorScheme): RemoteViews {
        val views = RemoteViews(context.packageName, R.layout.widget_totp)
        views.setInt(
            R.id.widget_totp_root,
            "setBackgroundResource",
            if (colors.isLight) R.drawable.widget_totp_bg_light else R.drawable.widget_totp_bg_dark,
        )
        views.setTextColor(R.id.widget_totp_issuer, colors.textPrimary.toArgb())
        views.setTextColor(R.id.widget_totp_account, colors.textSecondary.toArgb())
        views.setTextColor(R.id.widget_totp_code, colors.textPrimary.toArgb())
        views.setTextColor(R.id.widget_totp_hint, colors.textDim.toArgb())
        views.setTextColor(R.id.widget_totp_timer, colors.accentSoft.toArgb())
        views.setViewVisibility(R.id.widget_totp_timer, android.view.View.GONE)
        views.setOnClickPendingIntent(R.id.widget_totp_root, togglePendingIntent(widgetId))
        return views
    }

    private fun unbound(widgetId: Int, colors: ZColorScheme): RemoteViews {
        val views = base(widgetId, colors)
        views.setTextViewText(R.id.widget_totp_issuer, "Authentication code")
        views.setTextViewText(R.id.widget_totp_account, "")
        views.setTextViewText(R.id.widget_totp_code, masked(Totp.DEFAULT_DIGITS))
        views.setTextColor(R.id.widget_totp_code, colors.textDim.toArgb())
        views.setTextViewText(R.id.widget_totp_hint, "Tap to choose an account")
        // 割り当てが無いものは、押したら選び直しへ。ホーム画面の長押しに頼らずに直せる
        views.setOnClickPendingIntent(R.id.widget_totp_root, configurePendingIntent(widgetId))
        return views
    }

    private fun hidden(
        widgetId: Int,
        binding: OtpWidgetStore.Binding,
        colors: ZColorScheme,
    ): RemoteViews {
        val views = labelled(widgetId, binding, colors)
        views.setTextViewText(R.id.widget_totp_code, masked(binding.digits))
        views.setTextColor(R.id.widget_totp_code, colors.textDim.toArgb())
        views.setTextViewText(R.id.widget_totp_hint, "Tap to show")
        return views
    }

    private fun notice(
        widgetId: Int,
        binding: OtpWidgetStore.Binding,
        colors: ZColorScheme,
        message: String,
    ): RemoteViews {
        val views = labelled(widgetId, binding, colors)
        views.setTextViewText(R.id.widget_totp_code, masked(binding.digits))
        views.setTextColor(R.id.widget_totp_code, colors.textDim.toArgb())
        views.setTextViewText(R.id.widget_totp_hint, message)
        views.setTextColor(R.id.widget_totp_hint, colors.statusAmber.toArgb())
        return views
    }

    private fun code(
        widgetId: Int,
        entry: OtpEntry,
        colors: ZColorScheme,
        now: Long,
    ): RemoteViews {
        val views = base(widgetId, colors)
        views.setTextViewText(R.id.widget_totp_issuer, entry.label)
        views.setTextViewText(R.id.widget_totp_account, secondLine(entry.issuer, entry.account))
        views.setTextViewText(
            R.id.widget_totp_code,
            Totp.group(
                Totp.code(
                    secret = entry.secret,
                    timeMillis = now,
                    periodSeconds = entry.periodSeconds,
                    digits = entry.digits,
                    algorithm = entry.algorithm,
                )
            ),
        )
        views.setTextViewText(R.id.widget_totp_hint, "")

        // 残り秒は Chronometer に数えさせる。こちらから毎秒描き直すと、そのたびに
        // プロセスが起きることになる（1 回の表示で 30 回）
        val expiresIn = TotpReveal.codeExpiresAt(now, entry.periodSeconds) - now
        views.setViewVisibility(R.id.widget_totp_timer, android.view.View.VISIBLE)
        views.setChronometer(
            R.id.widget_totp_timer,
            SystemClock.elapsedRealtime() + expiresIn,
            null,
            true,
        )
        views.setChronometerCountDown(R.id.widget_totp_timer, true)
        return views
    }

    private fun labelled(
        widgetId: Int,
        binding: OtpWidgetStore.Binding,
        colors: ZColorScheme,
    ): RemoteViews {
        val views = base(widgetId, colors)
        val label = binding.issuer.ifBlank { binding.account }
        views.setTextViewText(R.id.widget_totp_issuer, label.ifBlank { "Authentication code" })
        views.setTextViewText(R.id.widget_totp_account, secondLine(binding.issuer, binding.account))
        return views
    }

    /** 発行元を 1 行目に出しているので、2 行目はアカウント名。発行元が無いときは空 */
    private fun secondLine(issuer: String, account: String): String =
        if (issuer.isBlank()) "" else account

    private fun masked(digits: Int): String =
        Totp.group("•".repeat(digits.coerceIn(Totp.DIGIT_RANGE)))

    // ---- 配色 ---------------------------------------------------------------

    /**
     * アプリ側で明暗を固定できるので、`-night` リソースには任せられない
     * （端末が暗くても明るい配色に留められる）。保存された設定をそのまま見る。
     */
    private suspend fun colors(): ZColorScheme {
        val mode = runCatching { preferences.state.first().themeMode }.getOrDefault(ThemeMode.SYSTEM)
        val dark = when (mode) {
            ThemeMode.DARK -> true
            ThemeMode.LIGHT -> false
            ThemeMode.SYSTEM -> systemDark()
        }
        return if (dark) ZDarkColors else ZLightColors
    }

    private fun systemDark(): Boolean {
        val mask = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return mask == Configuration.UI_MODE_NIGHT_YES
    }

    // ---- 起こし直し ---------------------------------------------------------

    private fun scheduleTick(widgetId: Int, at: Long?) {
        val alarms = context.getSystemService(AlarmManager::class.java) ?: return
        val pending = tickPendingIntent(widgetId)
        if (at == null) {
            alarms.cancel(pending)
            return
        }
        // 正確なアラームの権限は取らない。コードが見えているあいだホーム画面は前面なので、
        // 数百ミリ秒の遅れしか出ない ― そのために目覚まし権限を求める釣り合いではない
        alarms.set(AlarmManager.RTC, at, pending)
    }

    private fun cancelTick(widgetId: Int) {
        context.getSystemService(AlarmManager::class.java)?.cancel(tickPendingIntent(widgetId))
    }

    private fun togglePendingIntent(widgetId: Int) = broadcast(ACTION_TOGGLE, widgetId)

    private fun tickPendingIntent(widgetId: Int) = broadcast(ACTION_TICK, widgetId)

    private fun broadcast(action: String, widgetId: Int): PendingIntent {
        val intent = Intent().apply {
            component = ComponentName(context, ACTION_CLASS)
            this.action = action
            putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId)
            // データで区別しないと、同じ action の PendingIntent が使い回されて
            // どのウィジェットを押しても最後の 1 つが動く
            data = android.net.Uri.parse("zlauncher://totp/$widgetId")
        }
        return PendingIntent.getBroadcast(
            context,
            widgetId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
    }

    private fun configurePendingIntent(widgetId: Int): PendingIntent {
        val intent = Intent().apply {
            setClassName(context, CONFIG_CLASS)
            putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            data = android.net.Uri.parse("zlauncher://totp/config/$widgetId")
        }
        return PendingIntent.getActivity(
            context,
            widgetId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
    }

    companion object {
        const val ACTION_TOGGLE = "com.example.zlauncher.action.TOTP_WIDGET_TOGGLE"
        const val ACTION_TICK = "com.example.zlauncher.action.TOTP_WIDGET_TICK"
        private const val PROVIDER_CLASS = "com.example.zlauncher.ui.widgets.totp.TotpWidgetProvider"
        private const val ACTION_CLASS = "com.example.zlauncher.ui.widgets.totp.TotpWidgetActionReceiver"
        private const val CONFIG_CLASS = "com.example.zlauncher.ui.widgets.totp.TotpWidgetConfigActivity"
    }
}
