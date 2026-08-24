package com.example.zlauncher.data.prefs

import com.example.zlauncher.domain.model.AppSortOrder
import com.example.zlauncher.domain.model.CardLayout
import com.example.zlauncher.domain.model.DEFAULT_CARD_LAYOUT
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
) {
    companion object {
        const val CURRENT_VERSION = 1
        const val MAX_FAVORITES = 4
    }
}

/** 旧バージョンからの移行。今は 1 のみなので素通し */
fun LauncherState.migrate(): LauncherState = when (version) {
    LauncherState.CURRENT_VERSION -> this
    else -> copy(version = LauncherState.CURRENT_VERSION)
}
