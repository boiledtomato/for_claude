package com.example.zlauncher.core.otp

import java.net.URLDecoder

/**
 * 取り込み用の 1 件分。まだ保管していない「読み取れたもの」を表す。
 *
 * 鍵は Base32 のまま持ち歩かず、復号済みのバイト列で持つ。保管時にすぐ暗号化する。
 */
data class OtpDraft(
    val issuer: String,
    val account: String,
    val secret: ByteArray,
    val algorithm: OtpAlgorithm = OtpAlgorithm.SHA1,
    val digits: Int = Totp.DEFAULT_DIGITS,
    val periodSeconds: Int = Totp.DEFAULT_PERIOD_SECONDS,
) {
    /** 表示名。発行元が無いものもあるので、その場合はアカウントだけ */
    val label: String get() = if (issuer.isBlank()) account else issuer

    // ByteArray を持つので equals/hashCode を自前で定義する（既定だと参照比較になる）
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is OtpDraft) return false
        return issuer == other.issuer &&
            account == other.account &&
            secret.contentEquals(other.secret) &&
            algorithm == other.algorithm &&
            digits == other.digits &&
            periodSeconds == other.periodSeconds
    }

    override fun hashCode(): Int {
        var result = issuer.hashCode()
        result = 31 * result + account.hashCode()
        result = 31 * result + secret.contentHashCode()
        result = 31 * result + algorithm.hashCode()
        result = 31 * result + digits
        result = 31 * result + periodSeconds
        return result
    }

    /** ログや例外に鍵が出ないようにする。データクラスの既定 toString は中身を全部出す */
    override fun toString(): String = "OtpDraft($label, ${digits}digits/${periodSeconds}s)"
}

/**
 * `otpauth://totp/...` の読み取り。
 *
 * **貼り付けられた文字列の中から拾う**形にしてある。1 行に 1 件並べたもの、JSON の中に
 * URI が埋まっているもの、1 件だけのものが、どれも同じ手順で入る。移行のたびに形式を
 * 選ばせる画面を作っても、結局どれを選べばいいのか分からない。
 *
 * 対応しないもの: `otpauth://hotp`（カウンタ式。使う側が「今いくつ目か」を持つ必要があり、
 * ずれると復旧できない）と、パスフレーズで暗号化されたエクスポート。
 */
object OtpUri {

    private val URI_PATTERN = Regex("""otpauth://totp/[^\s"'<>\\]+""", RegexOption.IGNORE_CASE)

    /** 文字列中のすべての `otpauth://totp/` を読み取る。読めなかったものは黙って飛ばす */
    fun parseAll(text: String): List<OtpDraft> =
        URI_PATTERN.findAll(text).mapNotNull { parse(it.value) }.toList()

    fun parse(uri: String): OtpDraft? {
        val trimmed = uri.trim()
        if (!trimmed.startsWith("otpauth://totp/", ignoreCase = true)) return null

        val withoutScheme = trimmed.removeRange(0, "otpauth://totp/".length)
        val labelPart = withoutScheme.substringBefore('?')
        val query = withoutScheme.substringAfter('?', "")
        val params = query.split('&')
            .mapNotNull { pair ->
                if (pair.isBlank()) return@mapNotNull null
                val key = pair.substringBefore('=').lowercase()
                val value = decode(pair.substringAfter('=', ""))
                key to value
            }
            .toMap()

        val secretRaw = params["secret"].orEmpty()
        if (secretRaw.isBlank()) return null
        val secret = runCatching { Base32.decode(secretRaw) }.getOrNull() ?: return null
        if (secret.isEmpty()) return null

        // ラベルは "発行元:アカウント" の形。発行元は issuer パラメータが優先される
        val label = decode(labelPart)
        val labelIssuer = label.substringBefore(':', "").trim()
        val account = (if (label.contains(':')) label.substringAfter(':') else label).trim()
        val issuer = params["issuer"]?.trim().takeUnless { it.isNullOrBlank() } ?: labelIssuer

        return OtpDraft(
            issuer = issuer,
            account = account,
            secret = secret,
            algorithm = OtpAlgorithm.parse(params["algorithm"]),
            digits = params["digits"]?.toIntOrNull()?.coerceIn(Totp.DIGIT_RANGE) ?: Totp.DEFAULT_DIGITS,
            periodSeconds = params["period"]?.toIntOrNull()?.takeIf { it > 0 } ?: Totp.DEFAULT_PERIOD_SECONDS,
        )
    }

    private fun decode(value: String): String =
        runCatching { URLDecoder.decode(value, "UTF-8") }.getOrDefault(value)
}
