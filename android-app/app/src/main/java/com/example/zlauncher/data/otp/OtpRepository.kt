package com.example.zlauncher.data.otp

import com.example.zlauncher.core.otp.OtpDraft
import com.example.zlauncher.domain.model.OtpEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 認証コードの一覧を持つ。
 *
 * 保管庫は暗号化されたファイル 1 本なので、読み書きは常に全件。件数は多くても数十で、
 * 1 件ずつ差分を書く仕組みを作るほどの量にはならない。
 *
 * 読み込みは**画面を開いたときだけ**行う（[ensureLoaded]）。ホームに居るあいだずっと
 * 復号した鍵をメモリに置く必要はない。
 */
@Singleton
class OtpRepository @Inject constructor(
    private val vault: OtpVault,
) {
    private val _entries = MutableStateFlow<List<OtpEntry>>(emptyList())
    val entries: StateFlow<List<OtpEntry>> = _entries.asStateFlow()

    /**
     * 保管庫の状態。
     *
     * **読めていないときに書かせない**ための鍵になる。読めなかったのを「空」として扱うと、
     * そのあと 1 件足した瞬間に、残っていた保管庫を上書きして消してしまう。
     */
    enum class Status { EMPTY, READY, LOCKED, UNREADABLE }

    private val _status = MutableStateFlow(Status.EMPTY)
    val status: StateFlow<Status> = _status.asStateFlow()

    private val mutex = Mutex()
    private var loaded = false

    suspend fun ensureLoaded() = mutex.withLock {
        if (loaded) return@withLock
        withContext(Dispatchers.IO) {
            when (val result = vault.load()) {
                is OtpVault.Result.Ready -> {
                    _entries.value = result.entries
                    _status.value = Status.READY
                    loaded = true
                }
                OtpVault.Result.Locked -> _status.value = Status.LOCKED
                OtpVault.Result.Unreadable -> _status.value = Status.UNREADABLE
            }
        }
    }

    /**
     * 読めなくなった保管庫を捨てて作り直す。
     *
     * 鍵ごと消えているので中身は取り出せないが、**消すのは利用者が決めること**。
     * 画面側で確認を取ってから呼ぶ。
     */
    suspend fun discardUnreadable() = mutex.withLock {
        withContext(Dispatchers.IO) { vault.clear() }
        _entries.value = emptyList()
        _status.value = Status.READY
        loaded = true
    }

    /** 取り込み。すでにある鍵と同じものは足さない（同じ QR を 2 回読んでも増えない） */
    suspend fun add(drafts: List<OtpDraft>): Int = mutex.withLock {
        if (drafts.isEmpty()) return@withLock 0
        val current = _entries.value
        val existing = current.map { it.secret.toList() to it.account }.toSet()

        val added = drafts.mapNotNull { draft ->
            if ((draft.secret.toList() to draft.account) in existing) return@mapNotNull null
            OtpEntry(
                id = UUID.randomUUID().toString(),
                issuer = draft.issuer,
                account = draft.account,
                secret = draft.secret,
                algorithm = draft.algorithm,
                digits = draft.digits,
                periodSeconds = draft.periodSeconds,
            )
        }
        if (added.isEmpty()) return@withLock 0
        persist(current + added)
        added.size
    }

    suspend fun rename(id: String, issuer: String, account: String) = mutex.withLock {
        persist(
            _entries.value.map {
                if (it.id == id) it.copy(issuer = issuer.trim(), account = account.trim()) else it
            }
        )
    }

    suspend fun remove(ids: Collection<String>) = mutex.withLock {
        if (ids.isEmpty()) return@withLock
        val drop = ids.toSet()
        persist(_entries.value.filterNot { it.id in drop })
    }

    suspend fun move(fromIndex: Int, toIndex: Int) = mutex.withLock {
        val list = _entries.value.toMutableList()
        if (fromIndex !in list.indices || toIndex !in list.indices) return@withLock
        list.add(toIndex, list.removeAt(fromIndex))
        persist(list)
    }

    /**
     * 書き込み。**読めている状態でなければ書かない。**
     *
     * ロック中や復号できない状態で書くと、その時点のメモリ（＝空）で上書きになる。
     */
    private suspend fun persist(entries: List<OtpEntry>) {
        if (!loaded) return
        _entries.value = entries
        withContext(Dispatchers.IO) {
            if (entries.isEmpty()) vault.clear() else vault.save(entries)
        }
        _status.value = Status.READY
    }

    /**
     * 画面を離れたときに鍵をメモリから落とす。
     *
     * 開き直せば復号し直すだけなので、見ていない間ずっと持っている理由が無い。
     */
    suspend fun unload() = mutex.withLock {
        _entries.value = emptyList()
        loaded = false
        _status.value = Status.EMPTY
    }
}
