package com.example.zlauncher.core.otp

/**
 * RFC 4648 の Base32。
 *
 * 認証サイトが出す共有鍵はこの形でしか渡ってこない。標準ライブラリに無いので自前で持つ。
 * 人が手で打ち込むことも、QR から貼り付けることもあるので、**受け取りは緩く**する:
 * 小文字・空白・ハイフン・末尾の `=` は取り除いてから読む。
 */
object Base32 {
    private const val ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567"

    class InvalidBase32(message: String) : IllegalArgumentException(message)

    fun decode(input: String): ByteArray {
        val cleaned = input.uppercase()
            .filterNot { it == '=' || it == ' ' || it == '-' || it == '\n' || it == '\r' || it == '\t' }
        if (cleaned.isEmpty()) return ByteArray(0)

        val out = ArrayList<Byte>(cleaned.length * 5 / 8 + 1)
        var buffer = 0
        var bits = 0
        for (ch in cleaned) {
            val value = ALPHABET.indexOf(ch)
            // 文字そのものはエラーに含めない。鍵の一部を例外メッセージやログに出さないため
            if (value < 0) throw InvalidBase32("not a base32 character")
            buffer = (buffer shl 5) or value
            bits += 5
            if (bits >= 8) {
                bits -= 8
                out += ((buffer shr bits) and 0xFF).toByte()
            }
        }
        return out.toByteArray()
    }
}
