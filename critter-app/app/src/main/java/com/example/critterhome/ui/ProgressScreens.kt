package com.example.critterhome.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.critterhome.data.BatteryLevel

/** バッジの色。住みかの色を順に使い回す */
@Composable
private fun badgeColor(index: Int) = Game.colors.habitats[index % Game.colors.habitats.size]

@Composable
fun BadgesScreen(ui: GameUi, onBack: () -> Unit) {
    val earned = ui.badges.count { it.earned }
    SubScreen(title = "BADGES $earned/${ui.badges.size}", onBack = onBack) {
        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
            RetroWindow(Modifier.fillMaxWidth(), padding = 10.dp) {
                ui.badges.forEachIndexed { index, item ->
                    Row(Modifier.fillMaxWidth().padding(vertical = 5.dp), verticalAlignment = Alignment.CenterVertically) {
                        BadgeEmblem(color = badgeColor(index), earned = item.earned, size = 30.dp)
                        Spacer(Modifier.width(10.dp))
                        Column(Modifier.weight(1f)) {
                            Text(
                                item.badge.name.uppercase(),
                                style = RetroType.Body.copy(fontSize = 11.sp),
                                color = if (item.earned) Game.colors.ink else Game.colors.inkDim,
                            )
                            Text(item.badge.description, style = RetroType.Small, color = Game.colors.inkSoft)
                        }
                    }
                }
            }
        }
    }
}

/** トレーナーカード。HP はここでも電池残量 ― ゲームの中でここだけが実測値 */
@Composable
fun CardScreen(ui: GameUi, battery: BatteryLevel, onBack: () -> Unit) {
    SubScreen(title = "TRAINER CARD", onBack = onBack) {
        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
            RetroWindow(Modifier.fillMaxWidth(), padding = 12.dp) {
                InfoLine("LEVEL", "Lv.${ui.trainerLevel}")
                StatBar("EXP", ui.trainerProgress, Game.colors.accent, height = 5.dp)
                Spacer(Modifier.height(8.dp))
                val hp = battery.percent / 100f
                StatBar("HP", hp, hpColor(hp))
                Text(
                    if (battery.charging) "${battery.percent}% · resting (charging)" else "${battery.percent}% · battery",
                    style = RetroType.Small,
                    color = Game.colors.inkSoft,
                    modifier = Modifier.align(Alignment.End),
                )
                Spacer(Modifier.height(8.dp))
                InfoLine("OPENED", "${ui.launches} apps")
                InfoLine("STREAK", "${ui.streakDays} days (best ${ui.bestStreak})")
                InfoLine("MET", "${ui.companions.count { it.met }}/${ui.companions.size} companions")
                InfoLine("BADGES", "${ui.badges.count { it.earned }}/${ui.badges.size}")
                Spacer(Modifier.height(10.dp))
                FlowRow(horizontalArrangement = Arrangement.spacedBy(4.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    ui.badges.forEachIndexed { index, item ->
                        BadgeEmblem(color = badgeColor(index), earned = item.earned, size = 24.dp)
                    }
                }
            }
        }
    }
}
