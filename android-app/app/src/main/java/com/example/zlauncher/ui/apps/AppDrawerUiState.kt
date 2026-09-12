package com.example.zlauncher.ui.apps

import com.example.zlauncher.domain.model.AppEntry
import com.example.zlauncher.domain.model.AppSortOrder
import com.example.zlauncher.domain.model.CardStatus

data class DrawerStatusChip(val label: String, val status: CardStatus)

data class AppDrawerUiState(
    val loading: Boolean = true,
    /** 検索で絞り込んだ結果。検索していないときは全アプリ */
    val apps: List<AppEntry> = emptyList(),
    val totalAppCount: Int = 0,
    val query: String = "",
    val favorites: List<AppEntry> = emptyList(),
    val sortOrder: AppSortOrder = AppSortOrder.LABEL,
    val statusChips: List<DrawerStatusChip> = emptyList(),
    val favoriteSlots: Int = 4,
) {
    val isSearching: Boolean get() = query.isNotBlank()

    val isEmptyResult: Boolean get() = isSearching && apps.isEmpty()

    val isFavoritesFull: Boolean get() = favorites.size >= favoriteSlots

    fun isFavorite(entry: AppEntry): Boolean =
        !entry.isWorkProfile && favorites.any { it.packageName == entry.packageName }
}
