package com.example.zlauncher.ui.dashboard

import androidx.compose.runtime.Composable
import com.example.zlauncher.data.dashboard.DashboardSnapshot
import com.example.zlauncher.domain.model.CardIds
import com.example.zlauncher.ui.dashboard.card.DataUsageCard
import com.example.zlauncher.ui.dashboard.card.DevicesCard
import com.example.zlauncher.ui.dashboard.card.EventsCard
import com.example.zlauncher.ui.dashboard.card.LatencyCard
import com.example.zlauncher.ui.dashboard.card.PolicyCard
import com.example.zlauncher.ui.dashboard.card.SecurityPostureCard
import com.example.zlauncher.ui.dashboard.card.ThreatsCard
import com.example.zlauncher.ui.dashboard.card.TunnelUptimeCard
import com.example.zlauncher.ui.dashboard.card.UserActivityCard

data class CardDefinition(
    val id: String,
    val title: String,
    val content: @Composable (DashboardSnapshot) -> Unit,
)

/**
 * カード ID と描画の対応表。**カードを増やす作業はここに 1 行足すだけ**にする。
 * 画面側（[DashboardScreen]）はこの表しか知らないので、カードが増えても変更されない。
 */
object CardCatalog {
    private val definitions: List<CardDefinition> = listOf(
        CardDefinition(CardIds.SECURITY_POSTURE, "セキュリティ体制") { SecurityPostureCard(it) },
        CardDefinition(CardIds.TUNNEL_UPTIME, "トンネル稼働率") { TunnelUptimeCard(it) },
        CardDefinition(CardIds.LATENCY, "平均遅延") { LatencyCard(it) },
        CardDefinition(CardIds.POLICY, "ポリシー適用") { PolicyCard(it) },
        CardDefinition(CardIds.THREATS, "脅威ブロック") { ThreatsCard(it) },
        CardDefinition(CardIds.DEVICES, "準拠デバイス") { DevicesCard(it) },
        CardDefinition(CardIds.EVENTS, "最近のイベント") { EventsCard(it) },
        CardDefinition(CardIds.DATA_USAGE, "データ転送量") { DataUsageCard(it) },
        CardDefinition(CardIds.USER_ACTIVITY, "ユーザーアクティビティ") { UserActivityCard(it) },
    )

    private val byId = definitions.associateBy { it.id }

    /** 未知の ID は null。描画側でスキップするので、保存済み配置に古い ID が残っていても落ちない */
    operator fun get(id: String): CardDefinition? = byId[id]
}
