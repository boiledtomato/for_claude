package com.example.zlauncher.data.dashboard

import com.example.zlauncher.data.prefs.LauncherPreferencesRepository
import com.example.zlauncher.domain.model.CardLayout
import com.example.zlauncher.domain.model.CardSpan
import com.example.zlauncher.domain.model.DEFAULT_CARD_LAYOUT
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * カードの並び順・幅・表示可否。カードの中身は保存しない。
 *
 * [reconcile] があるので、アプリ更新でカードが増減しても保存済みの配置は壊れない。
 * これが無いと、知らない ID を描画しようとして落ちる／新カードが永遠に出ない。
 */
@Singleton
class DashboardLayoutRepository @Inject constructor(
    private val preferences: LauncherPreferencesRepository,
) {
    val layout: Flow<List<CardLayout>> = preferences.state.map { reconcile(it.cards) }

    /** 並べ替えは順序リストごと差し替える。連続ドラッグ中にインデックスがずれないようにするため */
    suspend fun setLayout(cards: List<CardLayout>) = preferences.update { state ->
        state.copy(cards = reconcile(cards))
    }

    suspend fun toggleVisibility(id: String) = preferences.update { state ->
        state.copy(cards = reconcile(state.cards).map { if (it.id == id) it.copy(visible = !it.visible) else it })
    }

    suspend fun setSpan(id: String, span: CardSpan) = preferences.update { state ->
        state.copy(cards = reconcile(state.cards).map { if (it.id == id) it.copy(span = span) else it })
    }

    suspend fun resetToDefault() = preferences.update { it.copy(cards = DEFAULT_CARD_LAYOUT) }

    /** 保存済みの並びを尊重しつつ、消えた ID を捨て、増えた ID を末尾に足す */
    private fun reconcile(stored: List<CardLayout>): List<CardLayout> {
        val known = DEFAULT_CARD_LAYOUT.associateBy { it.id }
        val kept = stored.filter { it.id in known }
        val missing = DEFAULT_CARD_LAYOUT.filterNot { default -> kept.any { it.id == default.id } }
        return kept + missing
    }
}
