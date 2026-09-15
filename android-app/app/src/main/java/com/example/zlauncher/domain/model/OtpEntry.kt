package com.example.zlauncher.domain.model

import com.example.zlauncher.core.otp.OtpAlgorithm
import com.example.zlauncher.core.otp.Totp

/**
 * 保管している認証コード 1 件。
 *
 * **共有鍵を持つので、これを UI へそのまま渡さない。** 画面に出すのは
 * [com.example.zlauncher.ui.console.OtpCodeUi]（発行元・アカウント・今のコード・残り秒）だけ。
 * Compose の状態に鍵を置くと、状態の保存や開発ツールの覗き見の対象が増える。
 */
data class OtpEntry(
    val id: String,
    val issuer: String,
    val account: String,
    val secret: ByteArray,
    val algorithm: OtpAlgorithm = OtpAlgorithm.SHA1,
    val digits: Int = Totp.DEFAULT_DIGITS,
    val periodSeconds: Int = Totp.DEFAULT_PERIOD_SECONDS,
) {
    val label: String get() = if (issuer.isBlank()) account else issuer

    /** 検索対象。発行元でもアカウントでも引ける（件数が多いと一覧では追えない） */
    fun matches(query: String): Boolean {
        val needle = query.trim()
        if (needle.isEmpty()) return true
        return issuer.contains(needle, ignoreCase = true) || account.contains(needle, ignoreCase = true)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is OtpEntry) return false
        return id == other.id &&
            issuer == other.issuer &&
            account == other.account &&
            secret.contentEquals(other.secret) &&
            algorithm == other.algorithm &&
            digits == other.digits &&
            periodSeconds == other.periodSeconds
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + issuer.hashCode()
        result = 31 * result + account.hashCode()
        result = 31 * result + secret.contentHashCode()
        result = 31 * result + algorithm.hashCode()
        result = 31 * result + digits
        result = 31 * result + periodSeconds
        return result
    }

    /** 鍵を絶対に出さない。既定の toString はデータクラスの中身を全部並べる */
    override fun toString(): String = "OtpEntry($id, $label, ${digits}digits/${periodSeconds}s)"
}
