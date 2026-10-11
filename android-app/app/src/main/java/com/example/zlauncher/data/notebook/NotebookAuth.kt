package com.example.zlauncher.data.notebook

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.zlauncher.core.notebook.NotebookEndpoint
import com.google.android.gms.auth.api.identity.AuthorizationRequest
import com.google.android.gms.auth.api.identity.AuthorizationResult
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.common.api.Scope
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume

/**
 * Cloud の API を読むためのアクセストークン。
 *
 * **同意は 1 回、取得は毎回。** 初回だけ利用者に同意画面を出し（[Authorization.NeedsConsent]
 * の `PendingIntent` を設定画面から開く）、以後は同じ呼び出しが UI 無しでトークンを返す。
 * トークンは 1 時間で切れるので保存しない ― 保存しないことが、ここで一番大事な性質。
 *
 * **リフレッシュトークンは要求しない。** `requestOfflineAccess` は端末に長期の資格情報を
 * 置くことになるうえ、本来は自前のサーバーで交換するもの。ウィジェットの更新は
 * 前景でも背景でも同意済みなら通るので、必要が無い。
 *
 * 前提として、Google Cloud 側に **Android 型の OAuth クライアント**（このアプリの
 * パッケージ名と署名の SHA-1）が要る。これは利用者のプロジェクトでしか作れない。
 */
@Singleton
class NotebookAuth @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    sealed interface Authorization {
        data class Granted(val accessToken: String) : Authorization
        /** 同意がまだ。設定画面からこの `PendingIntent` を開かせる */
        data class NeedsConsent(val pendingIntent: PendingIntent) : Authorization
        data class Failed(val reason: String) : Authorization
    }

    private val request: AuthorizationRequest by lazy {
        AuthorizationRequest.builder()
            .setRequestedScopes(listOf(Scope(NotebookEndpoint.OAUTH_SCOPE)))
            .build()
    }

    /**
     * 同意済みならトークン、未同意なら開くべき画面を返す。
     *
     * `Identity.getAuthorizationClient` は Activity でも Context でも受けるので、
     * ウィジェットの更新（Activity が無い）からも同じ道を通れる。
     */
    suspend fun authorize(): Authorization = suspendCancellableCoroutine { continuation ->
        Identity.getAuthorizationClient(context)
            .authorize(request)
            .addOnSuccessListener { result ->
                continuation.resume(interpret(result))
            }
            .addOnFailureListener { error ->
                // Play 開発者サービスが無い端末、OAuth クライアント未登録などもここに来る
                continuation.resume(Authorization.Failed(error.message ?: "authorize failed"))
            }
    }

    /** 同意画面から戻ってきた結果を読む */
    fun fromConsentResult(data: Intent?): Authorization = runCatching {
        interpret(Identity.getAuthorizationClient(context).getAuthorizationResultFromIntent(data))
    }.getOrElse { Authorization.Failed(it.message ?: "consent failed") }

    private fun interpret(result: AuthorizationResult): Authorization {
        val pending = result.pendingIntent
        if (result.hasResolution() && pending != null) return Authorization.NeedsConsent(pending)
        val token = result.accessToken
        return if (token.isNullOrBlank()) {
            // 解決手段も示されずトークンも無い。同意できない状態なので設定を見直させる
            Authorization.Failed("no access token granted")
        } else {
            Authorization.Granted(token)
        }
    }
}
