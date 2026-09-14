package com.example.zlauncher.core.otp

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

/**
 * RFC 6238 Appendix B の試験値をそのまま固定する。
 *
 * 認証コードは「合っているか」を目視で確かめられない類のもので、1 桁ずれても
 * 「サイト側が悪い」ようにしか見えない。仕様が公開している値で留めておく。
 */
class TotpTest {

    private val sha1Seed = "12345678901234567890".toByteArray()
    private val sha256Seed = "12345678901234567890123456789012".toByteArray()
    private val sha512Seed =
        "1234567890123456789012345678901234567890123456789012345678901234".toByteArray()

    private val times = listOf(59L, 1111111109L, 1111111111L, 1234567890L, 2000000000L, 20000000000L)

    private fun codes(seed: ByteArray, algorithm: OtpAlgorithm) = times.map { seconds ->
        Totp.code(seed, seconds * 1000L, periodSeconds = 30, digits = 8, algorithm = algorithm)
    }

    @Test
    fun `rfc 6238 vectors for sha1`() {
        assertEquals(
            listOf("94287082", "07081804", "14050471", "89005924", "69279037", "65353130"),
            codes(sha1Seed, OtpAlgorithm.SHA1),
        )
    }

    @Test
    fun `rfc 6238 vectors for sha256`() {
        assertEquals(
            listOf("46119246", "68084774", "67062674", "91819424", "90698825", "77737706"),
            codes(sha256Seed, OtpAlgorithm.SHA256),
        )
    }

    @Test
    fun `rfc 6238 vectors for sha512`() {
        assertEquals(
            listOf("90693936", "25091201", "99943326", "93441116", "38618901", "47863826"),
            codes(sha512Seed, OtpAlgorithm.SHA512),
        )
    }

    /** 実際に使うのはほぼ 6 桁。8 桁の下 6 桁と一致する（頭を落とすのではなく剰余で決まる） */
    @Test
    fun `six digits keeps the low order digits and pads`() {
        assertEquals("005924", Totp.code(sha1Seed, 1234567890_000L, digits = 6))
    }

    @Test
    fun `the code only changes when the step changes`() {
        val first = Totp.code(sha1Seed, 30_000L)
        assertEquals(first, Totp.code(sha1Seed, 59_999L))
        assertEquals("94287082", Totp.code(sha1Seed, 59_000L, digits = 8))
    }

    // ---- 残り時間 -------------------------------------------------------------

    @Test
    fun `seconds remaining counts down and never reaches zero`() {
        assertEquals(30, Totp.secondsRemaining(0L))
        assertEquals(29, Totp.secondsRemaining(1_000L))
        assertEquals(1, Totp.secondsRemaining(29_000L))
        assertEquals(30, Totp.secondsRemaining(30_000L))
    }

    @Test
    fun `remaining fraction runs from one down to just above zero`() {
        assertEquals(1f, Totp.remainingFraction(0L), 0.001f)
        assertEquals(0.5f, Totp.remainingFraction(15_000L), 0.001f)
        assertEquals(1f, Totp.remainingFraction(30_000L), 0.001f)
    }

    /** 1970 より前（端末の時計が壊れている等）でも落ちない */
    @Test
    fun `negative time still produces a code`() {
        val code = Totp.code(sha1Seed, -5_000L)
        assertEquals(6, code.length)
        // -5 秒は「0 秒で切り替わる枠の 25 秒目」なので、残りは 5 秒
        assertEquals(5, Totp.secondsRemaining(-5_000L))
    }

    @Test
    fun `codes are grouped for reading`() {
        assertEquals("123 456", Totp.group("123456"))
        assertEquals("1234 5678", Totp.group("12345678"))
    }

    // ---- Base32 ---------------------------------------------------------------

    @Test
    fun `base32 follows rfc 4648`() {
        assertEquals("f", String(Base32.decode("MY======")))
        assertEquals("fo", String(Base32.decode("MZXQ====")))
        assertEquals("foo", String(Base32.decode("MZXW6===")))
        assertEquals("foob", String(Base32.decode("MZXW6YQ=")))
        assertEquals("fooba", String(Base32.decode("MZXW6YTB")))
        assertEquals("foobar", String(Base32.decode("MZXW6YTBOI======")))
    }

    /** 手入力や QR の貼り付けを想定して、小文字・空白・ハイフンは落として読む */
    @Test
    fun `base32 tolerates how people paste keys`() {
        val expected = "12345678901234567890".toByteArray()
        assertEquals(
            expected.toList(),
            Base32.decode("gezd gnbv gy3t qojq gezd gnbv gy3t qojq").toList(),
        )
        assertEquals(
            expected.toList(),
            Base32.decode("GEZDGNBV-GY3TQOJQ-GEZDGNBV-GY3TQOJQ").toList(),
        )
    }

    @Test(expected = Base32.InvalidBase32::class)
    fun `base32 rejects characters outside the alphabet`() {
        Base32.decode("MZXW6YTB!")
    }

    // ---- otpauth:// -----------------------------------------------------------

    @Test
    fun `otpauth uri carries issuer account and parameters`() {
        val draft = OtpUri.parse(
            "otpauth://totp/GitHub:octocat%40example.com" +
                "?secret=GEZDGNBVGY3TQOJQ&issuer=GitHub&algorithm=SHA256&digits=8&period=60"
        )!!

        assertEquals("GitHub", draft.issuer)
        assertEquals("octocat@example.com", draft.account)
        assertEquals(OtpAlgorithm.SHA256, draft.algorithm)
        assertEquals(8, draft.digits)
        assertEquals(60, draft.periodSeconds)
        assertEquals("1234567890".toByteArray().toList(), draft.secret.toList())
    }

    @Test
    fun `otpauth uri falls back to the label issuer and the defaults`() {
        val draft = OtpUri.parse("otpauth://totp/AWS:root?secret=MZXW6YTB")!!

        assertEquals("AWS", draft.issuer)
        assertEquals("root", draft.account)
        assertEquals(OtpAlgorithm.SHA1, draft.algorithm)
        assertEquals(6, draft.digits)
        assertEquals(30, draft.periodSeconds)
    }

    @Test
    fun `a uri without a secret is not an entry`() {
        assertNull(OtpUri.parse("otpauth://totp/Example:me?issuer=Example"))
        assertNull(OtpUri.parse("otpauth://totp/Example:me?secret="))
    }

    /** カウンタ式は取り込まない。ずれると復旧できないので、黙って通さない */
    @Test
    fun `hotp is not accepted`() {
        assertNull(OtpUri.parse("otpauth://hotp/Example:me?secret=MZXW6YTB&counter=1"))
    }

    /**
     * 貼り付けは「テキストの中から拾う」。行で並べたものも、JSON に埋まったものも同じ手順で入る
     * ― 移行のたびに形式を選ばせる画面を作っても、どれを選ぶべきか分からない
     */
    @Test
    fun `every uri in a pasted blob is picked up`() {
        val pasted = """
            ここに貼り付けました
            otpauth://totp/A:one?secret=MZXW6YTB
            {"uri": "otpauth://totp/B:two?secret=MZXW6YTBOI======", "note": "x"}
            otpauth://totp/C:three?secret=NOT_BASE32!!
        """.trimIndent()

        val drafts = OtpUri.parseAll(pasted)

        // 3 件目は鍵が壊れているので落ちる。残りは通す（1 件の失敗で全部止めない）
        assertEquals(listOf("A", "B"), drafts.map { it.issuer })
        assertEquals(listOf("one", "two"), drafts.map { it.account })
    }

    @Test
    fun `drafts never print the secret`() {
        val draft = OtpUri.parse("otpauth://totp/GitHub:me?secret=GEZDGNBVGY3TQOJQ")!!
        val text = draft.toString()
        assertEquals(false, text.contains("1234"))
        assertEquals(true, text.contains("GitHub"))
    }
}
