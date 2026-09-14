package com.example.zlauncher.core.otp

import java.net.URLDecoder
import android.util.Base64

/**
 * Google Authenticator の書き出し（`otpauth-migration://offline?data=…`）の読み取り。
 *
 * 中身は base64 の protobuf。何十件も登録がある移行では、1 件ずつ QR を読み直すより
 * これを 1 回貼るほうが速い ― 実際、件数が多いほど他の手段は現実的でなくなる。
 *
 * **注意: この形式は公開仕様ではない。** スキーマは広く知られている内容に基づく実装で、
 * 合成データ（`OtpMigrationTest`）で固定してあるが、本物の書き出しに対しては検証できて
 * いない。取り込み後は必ず、元のアプリと**コードを見比べてから**元を消すこと。
 *
 * 対応する形:
 * ```
 * message MigrationPayload { repeated OtpParameters otp_parameters = 1; … }
 * message OtpParameters {
 *   bytes  secret    = 1;
 *   string name      = 2;
 *   string issuer    = 3;
 *   enum   algorithm = 4;   // 1 SHA1 / 2 SHA256 / 3 SHA512
 *   enum   digits    = 5;   // 1 SIX / 2 EIGHT
 *   enum   type      = 6;   // 1 HOTP / 2 TOTP
 * }
 * ```
 */
object OtpMigration {

    private val URI_PATTERN = Regex("""otpauth-migration://offline\?[^\s"'<>\\]+""", RegexOption.IGNORE_CASE)

    fun parseAll(text: String): List<OtpDraft> =
        URI_PATTERN.findAll(text).flatMap { parse(it.value).asSequence() }.toList()

    fun parse(uri: String): List<OtpDraft> {
        val query = uri.substringAfter('?', "")
        val data = query.split('&')
            .firstOrNull { it.substringBefore('=').equals("data", ignoreCase = true) }
            ?.substringAfter('=', "")
            ?: return emptyList()

        val decoded = runCatching { URLDecoder.decode(data, "UTF-8") }.getOrDefault(data)
        val bytes = runCatching {
            Base64.decode(decoded, Base64.DEFAULT or Base64.URL_SAFE)
        }.getOrNull() ?: return emptyList()

        return runCatching { decodePayload(bytes) }.getOrDefault(emptyList())
    }

    /**
     * 中身の読み取り。Android の Base64 を通さないので、単体テストからも呼べる。
     *
     * **途中で壊れていても、そこまでに読めたものは返す。** 何十件かの 1 件が壊れていた
     * ときに全部が入らないより、入ったものを見せて残りを手で足せるほうがいい。
     */
    internal fun decodePayload(bytes: ByteArray): List<OtpDraft> {
        val drafts = mutableListOf<OtpDraft>()
        val reader = ProtoReader(bytes)
        try {
            while (reader.hasMore()) {
                val (field, wire) = reader.readTag()
                if (field == 1 && wire == WIRE_LENGTH) {
                    decodeParameters(reader.readBytes())?.let { drafts += it }
                } else {
                    reader.skip(wire)
                }
            }
        } catch (_: IllegalArgumentException) {
            // 壊れた境界に当たったら、そこで打ち切る
        } catch (_: IndexOutOfBoundsException) {
        }
        return drafts
    }

    private fun decodeParameters(bytes: ByteArray): OtpDraft? {
        // 1 件ぶんが壊れていても他の件は生かす
        return runCatching { readParameters(bytes) }.getOrNull()
    }

    private fun readParameters(bytes: ByteArray): OtpDraft? {
        var secret: ByteArray? = null
        var name = ""
        var issuer = ""
        var algorithm = OtpAlgorithm.SHA1
        var digits = Totp.DEFAULT_DIGITS
        var isTotp = true

        val reader = ProtoReader(bytes)
        while (reader.hasMore()) {
            val (field, wire) = reader.readTag()
            when {
                field == 1 && wire == WIRE_LENGTH -> secret = reader.readBytes()
                field == 2 && wire == WIRE_LENGTH -> name = reader.readString()
                field == 3 && wire == WIRE_LENGTH -> issuer = reader.readString()
                field == 4 && wire == WIRE_VARINT -> algorithm = when (reader.readVarint().toInt()) {
                    2 -> OtpAlgorithm.SHA256
                    3 -> OtpAlgorithm.SHA512
                    else -> OtpAlgorithm.SHA1
                }
                field == 5 && wire == WIRE_VARINT -> digits = if (reader.readVarint().toInt() == 2) 8 else 6
                // カウンタ式は取り込まない。ずれると使えなくなり、直す手立てが無い
                field == 6 && wire == WIRE_VARINT -> isTotp = reader.readVarint().toInt() != 1
                else -> reader.skip(wire)
            }
        }

        val key = secret
        if (key == null || key.isEmpty() || !isTotp) return null

        // 名前が "発行元:アカウント" になっていることがある。issuer が空のときだけ拾う
        val account = if (issuer.isBlank() && name.contains(':')) name.substringAfter(':').trim() else name.trim()
        val resolvedIssuer = issuer.ifBlank { name.substringBefore(':', "").trim() }

        return OtpDraft(
            issuer = resolvedIssuer,
            account = account,
            secret = key,
            algorithm = algorithm,
            digits = digits,
            // 書き出しに周期は入らない。Google Authenticator は 30 秒固定
            periodSeconds = Totp.DEFAULT_PERIOD_SECONDS,
        )
    }

    private const val WIRE_VARINT = 0
    private const val WIRE_64BIT = 1
    private const val WIRE_LENGTH = 2
    private const val WIRE_32BIT = 5

    /** 必要なだけの protobuf 読み取り。ライブラリを 1 つ増やすほどの話ではない */
    private class ProtoReader(private val bytes: ByteArray) {
        private var pos = 0

        fun hasMore(): Boolean = pos < bytes.size

        fun readTag(): Pair<Int, Int> {
            val key = readVarint().toInt()
            return (key ushr 3) to (key and 0x7)
        }

        fun readVarint(): Long {
            var result = 0L
            var shift = 0
            while (true) {
                require(pos < bytes.size) { "truncated varint" }
                val b = bytes[pos++].toInt() and 0xFF
                result = result or ((b and 0x7F).toLong() shl shift)
                if (b and 0x80 == 0) return result
                shift += 7
                require(shift < 64) { "varint too long" }
            }
        }

        fun readBytes(): ByteArray {
            val length = readVarint().toInt()
            require(length >= 0 && pos + length <= bytes.size) { "truncated field" }
            val out = bytes.copyOfRange(pos, pos + length)
            pos += length
            return out
        }

        fun readString(): String = String(readBytes(), Charsets.UTF_8)

        fun skip(wire: Int) {
            when (wire) {
                WIRE_VARINT -> readVarint()
                WIRE_64BIT -> pos += 8
                WIRE_LENGTH -> readBytes()
                WIRE_32BIT -> pos += 4
                else -> throw IllegalArgumentException("unknown wire type")
            }
            require(pos <= bytes.size) { "truncated field" }
        }
    }
}
