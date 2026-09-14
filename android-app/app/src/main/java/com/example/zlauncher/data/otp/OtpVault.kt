package com.example.zlauncher.data.otp

import android.app.KeyguardManager
import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Log
import com.example.zlauncher.core.otp.OtpAlgorithm
import com.example.zlauncher.core.otp.Totp
import com.example.zlauncher.domain.model.OtpEntry
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.io.File
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

/**
 * 共有鍵の保管。
 *
 * **ほかの設定（DataStore の JSON）と同じ場所に入れない。** あちらは配置を変えるたびに
 * 書き換わり、丸ごとコピーされる機会も多い。こちらは専用のファイルにして、Android Keystore の
 * 鍵で AES-GCM 暗号化する。鍵は端末から取り出せない領域にあり、アプリの UID に紐づく。
 *
 * 意図的にそうしている点:
 *
 * - **発行元やアカウント名も含めて、ファイルごと暗号化する。** どのサービスを使っているかも
 *   知られたくない情報なので、鍵だけ隠しても足りない
 * - **画面ロック中は復号できない**（`setUnlockedDeviceRequired`）。コードを見るのは画面が
 *   点いているときだけなので、これで失うものは無い
 * - コードの表示自体に生体認証は求めない（利用者の選択）。したがってこの暗号化が守るのは
 *   「端末から吸い出したファイル」であって、「端末を手にした人」ではない
 */
@Singleton
class OtpVault @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    @Serializable
    private data class VaultFile(
        val version: Int = CURRENT_VERSION,
        val entries: List<StoredEntry> = emptyList(),
    )

    @Serializable
    private data class StoredEntry(
        val id: String,
        val issuer: String,
        val account: String,
        /** 鍵そのもの。ファイル全体が暗号化されているので、ここは素の base64 */
        val secret: String,
        val algorithm: OtpAlgorithm = OtpAlgorithm.SHA1,
        val digits: Int = Totp.DEFAULT_DIGITS,
        val periodSeconds: Int = Totp.DEFAULT_PERIOD_SECONDS,
    )

    /**
     * 読み込みの結果。
     *
     * **「読めなかった」と「空だった」を絶対に混ぜない。** 混ぜると、読めなかったあとに
     * 1 件追加した時点で、残っていた保管庫を空のファイルで上書きしてしまう。認証コードは
     * 失うと元のサイトの復旧手続きに行くしかないので、ここは黙って先へ進ませない。
     */
    sealed interface Result {
        data class Ready(val entries: List<OtpEntry>) : Result

        /** 画面ロック中。解除して開き直せば読める */
        data object Locked : Result

        /** 鍵が消えた・ファイルが壊れた。読めないが、消してよいとは限らない */
        data object Unreadable : Result
    }

    private val json = Json { ignoreUnknownKeys = true; encodeDefaults = true }
    private val file: File get() = File(context.filesDir, FILE_NAME)

    @OptIn(ExperimentalEncodingApi::class)
    fun load(): Result {
        if (!file.exists()) return Result.Ready(emptyList())

        val raw = try {
            decrypt(file.readBytes())
        } catch (_: LockedByKeyguard) {
            return Result.Locked
        } catch (error: Exception) {
            if (isDeviceLocked()) return Result.Locked
            // 鍵が消えた（アプリのデータを消した・入れ直した）か、ファイルが壊れている。
            // 例外の中身は出さない ― 復号まわりの例外に入力の断片が混じることがある
            Log.w(TAG, "vault could not be decrypted (${error.javaClass.simpleName})")
            return Result.Unreadable
        }

        val parsed = runCatching { json.decodeFromString<VaultFile>(raw) }.getOrNull()
            ?: return Result.Unreadable
        val entries = parsed.entries.mapNotNull { stored ->
            val secret = runCatching { Base64.decode(stored.secret) }.getOrNull() ?: return@mapNotNull null
            OtpEntry(
                id = stored.id,
                issuer = stored.issuer,
                account = stored.account,
                secret = secret,
                algorithm = stored.algorithm,
                digits = stored.digits,
                periodSeconds = stored.periodSeconds,
            )
        }
        return Result.Ready(entries)
    }

    @OptIn(ExperimentalEncodingApi::class)
    fun save(entries: List<OtpEntry>) {
        val payload = VaultFile(
            entries = entries.map { entry ->
                StoredEntry(
                    id = entry.id,
                    issuer = entry.issuer,
                    account = entry.account,
                    secret = Base64.encode(entry.secret),
                    algorithm = entry.algorithm,
                    digits = entry.digits,
                    periodSeconds = entry.periodSeconds,
                )
            }
        )
        val bytes = encrypt(json.encodeToString(payload))
        // 書き換え中に電源が落ちても、元のファイルを壊さない
        val temp = File(context.filesDir, "$FILE_NAME.tmp")
        temp.writeBytes(bytes)
        if (!temp.renameTo(file)) {
            file.writeBytes(bytes)
            temp.delete()
        }
    }

    fun clear() {
        file.delete()
    }

    // ---- 暗号 ---------------------------------------------------------------

    private fun encrypt(plain: String): ByteArray {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, key())
        val body = cipher.doFinal(plain.toByteArray(Charsets.UTF_8))
        // IV は毎回作り直される。先頭に長さを 1 バイト付けて連結する
        return byteArrayOf(cipher.iv.size.toByte()) + cipher.iv + body
    }

    private fun decrypt(bytes: ByteArray): String {
        require(bytes.isNotEmpty()) { "empty vault" }
        val ivLength = bytes[0].toInt()
        require(ivLength in 1..16 && bytes.size > ivLength + 1) { "malformed vault" }
        val iv = bytes.copyOfRange(1, 1 + ivLength)
        val body = bytes.copyOfRange(1 + ivLength, bytes.size)

        val cipher = Cipher.getInstance(TRANSFORMATION)
        try {
            cipher.init(Cipher.DECRYPT_MODE, key(), GCMParameterSpec(TAG_BITS, iv))
        } catch (error: Exception) {
            // 画面ロック中に使えない鍵にしてあるので、ロックが理由かどうかで意味が変わる。
            // 例外の型で見分けようとすると端末差で外れるため、ロック状態そのものを見る
            if (isDeviceLocked()) throw LockedByKeyguard()
            throw error
        }
        return String(cipher.doFinal(body), Charsets.UTF_8)
    }

    private fun isDeviceLocked(): Boolean {
        val keyguard = context.getSystemService(KeyguardManager::class.java) ?: return false
        return keyguard.isDeviceLocked
    }

    private class LockedByKeyguard : IllegalStateException("device is locked")

    private fun key(): SecretKey {
        val store = KeyStore.getInstance(KEYSTORE).apply { load(null) }
        (store.getEntry(ALIAS, null) as? KeyStore.SecretKeyEntry)?.let { return it.secretKey }

        val generator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, KEYSTORE)
        generator.init(
            KeyGenParameterSpec.Builder(
                ALIAS,
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT,
            )
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .setKeySize(256)
                // 画面ロック中は使えない鍵にする。コードを見るのは画面が点いているときだけ
                .setUnlockedDeviceRequired(true)
                .build()
        )
        return generator.generateKey()
    }

    private companion object {
        const val TAG = "OtpVault"
        const val KEYSTORE = "AndroidKeyStore"
        const val ALIAS = "zlauncher_otp_v1"
        const val TRANSFORMATION = "AES/GCM/NoPadding"
        const val TAG_BITS = 128
        const val FILE_NAME = "otp_vault.bin"
        const val CURRENT_VERSION = 1
    }
}
