package com.example.zlauncher.core.otp

import kotlinx.serialization.Serializable
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

/** 共有鍵に使うハッシュ。既定は SHA1（ほとんどのサイトがこれ） */
@Serializable
enum class OtpAlgorithm(val macName: String) {
    SHA1("HmacSHA1"),
    SHA256("HmacSHA256"),
    SHA512("HmacSHA512"),
    ;

    companion object {
        fun parse(value: String?): OtpAlgorithm = when (value?.uppercase()?.replace("-", "")) {
            "SHA256" -> SHA256
            "SHA512" -> SHA512
            else -> SHA1
        }
    }
}

/**
 * RFC 6238 の TOTP。
 *
 * 時刻を [periodSeconds] で割った通し番号を HMAC にかけ、末尾 4 バイトの位置から
 * 31 ビットを取り出して 10 のべき乗で丸める（RFC 4226 の dynamic truncation）。
 *
 * **端末の時計がずれると全部外れる。** 認証側は普通 ±1 ステップを許すので、数十秒の
 * ずれなら通るが、分単位でずれている端末では何をしても合わない。[Totp.code] は端末時刻を
 * そのまま使う ― ここで勝手に補正すると、ずれていることに気付けなくなる。
 */
object Totp {

    const val DEFAULT_PERIOD_SECONDS = 30
    const val DEFAULT_DIGITS = 6
    val DIGIT_RANGE = 6..8

    fun code(
        secret: ByteArray,
        timeMillis: Long,
        periodSeconds: Int = DEFAULT_PERIOD_SECONDS,
        digits: Int = DEFAULT_DIGITS,
        algorithm: OtpAlgorithm = OtpAlgorithm.SHA1,
    ): String {
        require(periodSeconds > 0) { "period must be positive" }
        val counter = Math.floorDiv(timeMillis / 1000L, periodSeconds.toLong())
        val message = ByteArray(8)
        for (i in 7 downTo 0) {
            message[i] = ((counter ushr ((7 - i) * 8)) and 0xFF).toByte()
        }

        val mac = Mac.getInstance(algorithm.macName)
        mac.init(SecretKeySpec(secret, algorithm.macName))
        val hash = mac.doFinal(message)

        val offset = hash[hash.size - 1].toInt() and 0x0F
        val binary = ((hash[offset].toInt() and 0x7F) shl 24) or
            ((hash[offset + 1].toInt() and 0xFF) shl 16) or
            ((hash[offset + 2].toInt() and 0xFF) shl 8) or
            (hash[offset + 3].toInt() and 0xFF)

        val width = digits.coerceIn(DIGIT_RANGE)
        return (binary % POWERS.getValue(width)).toString().padStart(width, '0')
    }

    /** 今の番号が切り替わるまでの残り秒。0 にはならず、切り替わった瞬間に [periodSeconds] へ戻る */
    fun secondsRemaining(timeMillis: Long, periodSeconds: Int = DEFAULT_PERIOD_SECONDS): Int {
        require(periodSeconds > 0) { "period must be positive" }
        val elapsed = Math.floorMod(timeMillis / 1000L, periodSeconds.toLong()).toInt()
        return periodSeconds - elapsed
    }

    /** 残り時間の割合（1.0 = 出たばかり、0.0 に近い = もうすぐ切り替わる） */
    fun remainingFraction(timeMillis: Long, periodSeconds: Int = DEFAULT_PERIOD_SECONDS): Float {
        require(periodSeconds > 0) { "period must be positive" }
        val elapsedMillis = Math.floorMod(timeMillis, periodSeconds * 1000L)
        return 1f - elapsedMillis.toFloat() / (periodSeconds * 1000f)
    }

    /** 読みやすさのため 3 桁ずつ空ける。6 桁なら `123 456`、8 桁なら `1234 5678` */
    fun group(code: String): String = when (code.length) {
        6 -> "${code.take(3)} ${code.drop(3)}"
        8 -> "${code.take(4)} ${code.drop(4)}"
        else -> code
    }

    private val POWERS = mapOf(
        6 to 1_000_000,
        7 to 10_000_000,
        8 to 100_000_000,
    )
}
