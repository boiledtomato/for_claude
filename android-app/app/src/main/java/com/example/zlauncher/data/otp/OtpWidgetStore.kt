package com.example.zlauncher.data.otp

import android.content.Context
import com.example.zlauncher.core.otp.Totp
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 「どのウィジェットがどの登録を映しているか」。
 *
 * 置いてあるウィジェットの数だけ、`appWidgetId → 保管庫の id` を持つ。**共有鍵はここに
 * 入れない。** 鍵は [OtpVault]（Keystore で暗号化したファイル）にあり、こちらが持つのは
 * ランダムな id と、画面に出す名前だけ。
 *
 * 名前を素のまま持つのは意図的な妥協。保管庫はサービス名まで含めて暗号化しているが、
 * ホーム画面に置いた時点でその名前は端末を覗いた誰にでも見えている。再起動直後など
 * 保管庫を読めない場面でも枠の中身が消えないほうが、実際の使い勝手に合う。
 * ここに無いのは鍵とコードで、そこは変わらない。
 */
@Singleton
class OtpWidgetStore @Inject constructor(
    @ApplicationContext context: Context,
) {
    private val prefs = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)

    data class Binding(
        val entryId: String,
        val issuer: String,
        val account: String,
        /** 伏せ字の桁数を合わせるためだけに持つ。鍵からは何も分からない */
        val digits: Int = Totp.DEFAULT_DIGITS,
        val periodSeconds: Int = Totp.DEFAULT_PERIOD_SECONDS,
    )

    fun binding(widgetId: Int): Binding? {
        val entryId = prefs.getString(key(ENTRY, widgetId), null) ?: return null
        return Binding(
            entryId = entryId,
            issuer = prefs.getString(key(ISSUER, widgetId), "").orEmpty(),
            account = prefs.getString(key(ACCOUNT, widgetId), "").orEmpty(),
            digits = prefs.getInt(key(DIGITS, widgetId), Totp.DEFAULT_DIGITS),
            periodSeconds = prefs.getInt(key(PERIOD, widgetId), Totp.DEFAULT_PERIOD_SECONDS),
        )
    }

    fun bind(widgetId: Int, binding: Binding) {
        prefs.edit()
            .putString(key(ENTRY, widgetId), binding.entryId)
            .putString(key(ISSUER, widgetId), binding.issuer)
            .putString(key(ACCOUNT, widgetId), binding.account)
            .putInt(key(DIGITS, widgetId), binding.digits)
            .putInt(key(PERIOD, widgetId), binding.periodSeconds)
            .remove(key(UNTIL, widgetId))
            .apply()
    }

    /** コードを出しておく期限。0 は「隠れている」 */
    fun revealUntil(widgetId: Int): Long = prefs.getLong(key(UNTIL, widgetId), 0L)

    fun setRevealUntil(widgetId: Int, until: Long) {
        prefs.edit().putLong(key(UNTIL, widgetId), until).apply()
    }

    /** ホーム画面から外されたとき。残しておくと id が使い回されて別の登録が映る */
    fun forget(widgetId: Int) {
        prefs.edit()
            .remove(key(ENTRY, widgetId))
            .remove(key(ISSUER, widgetId))
            .remove(key(ACCOUNT, widgetId))
            .remove(key(DIGITS, widgetId))
            .remove(key(PERIOD, widgetId))
            .remove(key(UNTIL, widgetId))
            .apply()
    }

    private fun key(prefix: String, widgetId: Int) = "$prefix$widgetId"

    private companion object {
        const val FILE_NAME = "otp_widgets"
        const val ENTRY = "entry_"
        const val ISSUER = "issuer_"
        const val ACCOUNT = "account_"
        const val DIGITS = "digits_"
        const val PERIOD = "period_"
        const val UNTIL = "until_"
    }
}
