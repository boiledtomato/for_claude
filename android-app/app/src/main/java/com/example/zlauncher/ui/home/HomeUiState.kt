package com.example.zlauncher.ui.home

import com.example.zlauncher.domain.model.AppEntry
import com.example.zlauncher.domain.model.AppSortOrder
import com.example.zlauncher.domain.model.CardStatus

data class HomeStatusChip(val label: String, val status: CardStatus)

data class HomeUiState(
    val loading: Boolean = true,
    val apps: List<AppEntry> = emptyList(),
    val favorites: List<AppEntry> = emptyList(),
    val sortOrder: AppSortOrder = AppSortOrder.LABEL,
    val statusChips: List<HomeStatusChip> = emptyList(),
    val favoriteSlots: Int = 4,
) {
    val isFavoritesFull: Boolean get() = favorites.size >= favoriteSlots

    fun isFavorite(entry: AppEntry): Boolean = favorites.any { it.packageName == entry.packageName }
}
