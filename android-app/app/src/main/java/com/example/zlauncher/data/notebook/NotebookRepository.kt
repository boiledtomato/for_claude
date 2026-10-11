package com.example.zlauncher.data.notebook

import com.example.zlauncher.core.notebook.NotebookFetch
import com.example.zlauncher.core.notebook.NotebookStatus
import com.example.zlauncher.data.prefs.LauncherPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 設定・認証・取得・保存をつなぐところ。
 *
 * **取れなかったときに前回の一覧を消さない。** ウィジェットが突然空になるのは
 * 「壊れた」と読める一番よくない見え方なので、中身は残したまま理由の一行だけ差し替える。
 * 消すのは取得が成功したときだけ（＝本当に 0 件になったとき）。
 */
@Singleton
class NotebookRepository @Inject constructor(
    private val preferences: LauncherPreferencesRepository,
    private val auth: NotebookAuth,
    private val api: NotebookEnterpriseApi,
    private val store: NotebookCacheStore,
) {

    val config: Flow<NotebookConfig> = preferences.state.map { it.notebook }

    val cache: Flow<NotebookCache> = store.cache

    suspend fun setConfig(transform: (NotebookConfig) -> NotebookConfig) {
        preferences.update { it.copy(notebook = transform(it.notebook)) }
    }

    /** 同意画面が要るときはそれを返す。設定画面から開かせる */
    suspend fun authorize(): NotebookAuth.Authorization = auth.authorize()

    /**
     * 取り直して保存する。返り値は呼び出し側が「今の理由」を知るため。
     *
     * 認証と取得で失敗の形が違うので、両方を [NotebookFetch] に寄せてから 1 か所で保存する。
     */
    suspend fun refresh(): NotebookFetch {
        val config = preferences.state.first().notebook
        if (!config.isConfigured) return record(NotebookFetch.NotConfigured, needsConsent = false)

        val authorization = auth.authorize()
        val token = when (authorization) {
            is NotebookAuth.Authorization.Granted -> authorization.accessToken
            is NotebookAuth.Authorization.NeedsConsent ->
                return record(NotebookFetch.NeedsSignIn, needsConsent = true)
            is NotebookAuth.Authorization.Failed ->
                return record(NotebookFetch.Failed(authorization.reason), needsConsent = false)
        }

        return record(api.listNotebooks(config, token), needsConsent = false)
    }

    private suspend fun record(state: NotebookFetch, needsConsent: Boolean): NotebookFetch {
        store.write { current ->
            val notebooks = (state as? NotebookFetch.Ok)?.notebooks ?: current.notebooks
            val hasCached = notebooks.isNotEmpty()
            current.copy(
                notebooks = notebooks,
                // 成功したときだけ「いつの情報か」を進める
                fetchedAtMillis = if (state is NotebookFetch.Ok) {
                    System.currentTimeMillis()
                } else {
                    current.fetchedAtMillis
                },
                status = NotebookStatus.message(state, hasCached),
                showsList = NotebookStatus.showsList(state, hasCached),
                needsConsent = needsConsent,
            )
        }
        return state
    }
}
