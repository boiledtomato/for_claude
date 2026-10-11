package com.example.zlauncher.ui.notebookwidget

import java.util.concurrent.TimeUnit

/**
 * ウィジェットに出す文字の組み立て。**純粋なのでそのままテストにかけられる。**
 *
 * 日時をそのまま出さないのが肝。`2026-10-09T02:11:00Z` と出しても、それが今朝なのか
 * 先月なのかは読み取れない。ウィジェットで知りたいのは「新しいか古いか」なので、
 * 経過で言う。
 */
object NotebookWidgetText {

    /**
     * 一覧の 2 行目。**分かっているものだけ並べる。**
     *
     * 読み元で出せる項目が違う ― パイプラインからはソース数と最終同期、Enterprise からは
     * 権限・共有・最後に見た日時。どちらか片方しか埋まらないので、無いものは出さない。
     */
    fun meta(
        userRole: String = "",
        isShared: Boolean = false,
        lastViewed: String = "",
        sourceCount: Int = -1,
        syncedAt: String = "",
        nowMillis: Long,
    ): String {
        val parts = buildList {
            // -1 は「分からない」。0 件と混ぜない
            if (sourceCount >= 0) add(if (sourceCount == 1) "1 source" else "$sourceCount sources")
            relative(syncedAt, nowMillis)?.let { add("Synced $it") }
            if (userRole.isNotBlank()) add(role(userRole))
            if (isShared) add("Shared")
            relative(lastViewed, nowMillis)?.let { add("Opened $it") }
        }
        // 1 つも分からないこともある（プレビュー API なので項目が欠ける）。
        // そのときは 2 行目を空にして、題だけの行にする
        return parts.joinToString(" · ")
    }

    /** `OWNER` → `Owner`。API の定数をそのまま出すと画面だけ叫んでいるように見える */
    internal fun role(userRole: String): String =
        userRole.lowercase().replaceFirstChar { it.uppercase() }.replace('_', ' ')

    /** 見出しの「いつの情報か」 */
    fun refreshedAt(fetchedAtMillis: Long, nowMillis: Long): String = when {
        fetchedAtMillis <= 0L -> ""
        else -> "Updated ${elapsed(nowMillis - fetchedAtMillis)}"
    }

    /**
     * RFC3339 の時刻を「3 時間前」の形に。読めない / 未来の値は null（出さない）。
     *
     * 自前で切り出すのは、`Instant.parse` が端末の API 版で使えないことがあるのと、
     * 秒の小数やオフセット付きが混ざって来ても落ちないようにするため。
     */
    internal fun relative(rfc3339: String, nowMillis: Long): String? {
        val millis = parseMillis(rfc3339) ?: return null
        val delta = nowMillis - millis
        // 端末の時計がずれているだけで「-5 分前」と出すのは意味が無い
        if (delta < 0) return null
        return elapsed(delta)
    }

    private fun elapsed(delta: Long): String {
        val minutes = TimeUnit.MILLISECONDS.toMinutes(delta)
        val hours = TimeUnit.MILLISECONDS.toHours(delta)
        val days = TimeUnit.MILLISECONDS.toDays(delta)
        return when {
            minutes < 1 -> "just now"
            minutes < 60 -> "${minutes}m ago"
            hours < 24 -> "${hours}h ago"
            days < 30 -> "${days}d ago"
            else -> "${days / 30}mo ago"
        }
    }

    /** `2026-10-09T02:11:00Z` / 小数秒 / オフセット付きを受ける。駄目なら null */
    internal fun parseMillis(rfc3339: String): Long? {
        if (rfc3339.isBlank()) return null
        return runCatching {
            java.time.OffsetDateTime.parse(rfc3339).toInstant().toEpochMilli()
        }.recoverCatching {
            java.time.Instant.parse(rfc3339).toEpochMilli()
        }.getOrNull()
    }
}
