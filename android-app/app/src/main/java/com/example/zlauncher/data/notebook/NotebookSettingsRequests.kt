package com.example.zlauncher.data.notebook

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * ウィジェットから「設定を開かせてくれ」という合図。
 *
 * **ウィジェットは別プロセスで描かれる**ので、画面側の状態を直接触れない。できるのは
 * `PendingIntent` でこのアプリを起こすところまでで、起きたあとに何を開くかは
 * アプリ側が決める必要がある。その橋渡しがこれ。
 *
 * [MainActivity] が Intent の action を見て [request] を呼び、[ConsoleViewModel] が
 * 受けて Overview へ移動したうえで設定ダイアログを開かせる。
 *
 * `extraBufferCapacity = 1` にしてあるので、まだ誰も見ていない時点で呼んでも落ちない
 * （起動直後は ViewModel がまだ collect していない）。
 */
@Singleton
class NotebookSettingsRequests @Inject constructor() {

    private val _requests = MutableSharedFlow<Unit>(
        replay = 1,
        extraBufferCapacity = 1,
    )

    /**
     * replay を 1 にしてあるのは、**起動のきっかけがこの合図そのもの**だから。
     * アプリが死んでいる状態でウィジェットを押すと、Activity と ViewModel が
     * 生まれるより先に合図が出る ― 残しておかないと黙って消える。
     */
    val requests: SharedFlow<Unit> = _requests.asSharedFlow()

    fun request() {
        _requests.tryEmit(Unit)
    }

    /** 1 度開いたら消す。replay を残したままだと画面を作り直すたびに開く */
    fun consume() {
        _requests.resetReplayCache()
    }
}
