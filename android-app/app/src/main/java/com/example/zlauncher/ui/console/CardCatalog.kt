package com.example.zlauncher.ui.console

import androidx.compose.runtime.Composable
import com.example.zlauncher.domain.model.CardIds
import com.example.zlauncher.ui.console.card.AppsCard
import com.example.zlauncher.ui.console.card.BatteryCard
import com.example.zlauncher.ui.console.card.CardContext
import com.example.zlauncher.ui.console.card.MemoryCard
import com.example.zlauncher.ui.console.card.NetworkCard
import com.example.zlauncher.ui.console.card.RecentAppsCard
import com.example.zlauncher.ui.console.card.StorageCard
import com.example.zlauncher.ui.console.card.UptimeCard

data class CardDefinition(
    val id: String,
    val title: String,
    val content: @Composable (CardContext) -> Unit,
)

/**
 * カード ID と描画の対応表。**カードを増やす作業はここに 1 行足すだけ**にする。
 * 画面側はこの表しか知らないので、カードが増えても変更されない。
 */
object CardCatalog {
    private val definitions: List<CardDefinition> = listOf(
        CardDefinition(CardIds.BATTERY, "バッテリー") { BatteryCard(it) },
        CardDefinition(CardIds.STORAGE, "ストレージ") { StorageCard(it) },
        CardDefinition(CardIds.MEMORY, "メモリ") { MemoryCard(it) },
        CardDefinition(CardIds.NETWORK, "ネットワーク") { NetworkCard(it) },
        CardDefinition(CardIds.UPTIME, "連続稼働") { UptimeCard(it) },
        CardDefinition(CardIds.APPS, "アプリ") { AppsCard(it) },
        CardDefinition(CardIds.RECENT_APPS, "最近追加したアプリ") { RecentAppsCard(it) },
    )

    private val byId = definitions.associateBy { it.id }

    /** 未知の ID は null。描画側でスキップするので、保存済み配置に古い ID が残っていても落ちない */
    operator fun get(id: String): CardDefinition? = byId[id]
}
