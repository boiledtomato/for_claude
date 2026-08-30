package com.example.zlauncher.data.prefs

import com.example.zlauncher.domain.model.AppCategory
import com.example.zlauncher.domain.model.AppSortOrder
import com.example.zlauncher.domain.model.CatalogDiff
import com.example.zlauncher.domain.model.CardLayout
import com.example.zlauncher.domain.model.DEFAULT_CARD_LAYOUT
import com.example.zlauncher.domain.model.WidgetPlacement
import kotlinx.serialization.Serializable

/**
 * 永続化する状態のすべて。
 *
 * [version] は最初から持たせておく。後から CardSpan を増やす等の変更をしたときに
 * [migrate] で移行できるようにするため。
 */
@Serializable
data class LauncherState(
    val version: Int = CURRENT_VERSION,
    val sortOrder: AppSortOrder = AppSortOrder.LABEL,
    /** お気に入り（ドック）。ComponentName ではなく packageName で持つ */
    val favorites: List<String> = emptyList(),
    val cards: List<CardLayout> = DEFAULT_CARD_LAYOUT,
    /** ホーム上部に並べるウィジェット。既定値があるので旧バージョンの JSON もそのまま読める */
    val widgets: List<WidgetPlacement> = emptyList(),
    /** コンソール左レールのカテゴリー（ユーザーが作る） */
    val categories: List<AppCategory> = emptyList(),
    /** レール上部にピン留めするアプリ。最大 [MAX_PINNED] 件 */
    val pinnedApps: List<String> = emptyList(),
    /**
     * レール上部のピン留めを開いているか。
     *
     * 枠が 4 つになるとレールの上半分をピンが占め、カテゴリーが押し出される。
     * 畳めるようにして、使わないときは場所を返せるようにする。
     */
    val pinnedExpanded: Boolean = true,
    /** アイコンをテーマ色に寄せる（モノクロレイヤーを持つアプリのみ、Android 13 以降） */
    val themedIcons: Boolean = false,
    /** 取り込み済み URL カテゴリー CSV のファイル名。空なら同梱版のまま */
    val catalogRevision: String = "",
    val catalogFetchedAtMillis: Long = 0L,
    /**
     * 見つかったがまだ適用していない改訂差分。
     * 黙って差し替えず、ユーザーが確認するまでここに置いておく。
     */
    val pendingCatalogDiff: CatalogDiff? = null,
) {
    companion object {
        const val CURRENT_VERSION = 1
        const val MAX_FAVORITES = 4
        const val MAX_PINNED = 4
    }
}

/** 旧バージョンからの移行。今は 1 のみなので素通し */
fun LauncherState.migrate(): LauncherState = when (version) {
    LauncherState.CURRENT_VERSION -> this
    else -> copy(version = LauncherState.CURRENT_VERSION)
}
