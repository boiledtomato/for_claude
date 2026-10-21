package com.example.critterhome.ui

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.critterhome.data.AppEntry
import com.example.critterhome.game.GameRules
import java.time.LocalDate
import kotlin.math.ceil

/**
 * ステータス画面。上に姿と数値、下に「わざ」＝ この住みかのアプリ。わざを使うと
 * そのアプリが開き、この相棒に経験値が入る。
 */
@Composable
fun SummaryScreen(
    companion: CompanionUi,
    iconProvider: suspend (AppEntry) -> Bitmap?,
    onUseMove: (AppEntry) -> Unit,
    onBack: () -> Unit,
) {
    val color = Game.colors.habitat(companion.habitat)
    SubScreen(title = "SUMMARY", onBack = onBack) {
        Column(
            Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            RetroWindow(Modifier.fillMaxWidth(), padding = 12.dp) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    CompanionSprite(companion, size = 112.dp)
                    Spacer(Modifier.width(12.dp))
                    Column(Modifier.weight(1f)) {
                        Text(companion.name, style = RetroType.Title, color = Game.colors.ink, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        Text("Lv.${companion.level}", style = RetroType.Body, color = Game.colors.ink)
                        Spacer(Modifier.height(4.dp))
                        Text("TYPE/${companion.habitat.type}", style = RetroType.Small, color = color)
                        Text("STAGE ${companion.stage + 1}/3", style = RetroType.Small, color = Game.colors.inkSoft)
                    }
                }
                Spacer(Modifier.height(10.dp))
                val hpDays = ceil(companion.hp * GameRules.HP_DAYS).toInt()
                StatBar("HP", companion.hp, hpColor(companion.hp))
                Text(
                    if (companion.fainted) "Fainted. Use any of its moves to revive it." else "HP $hpDays/${GameRules.HP_DAYS}",
                    style = RetroType.Small,
                    color = if (companion.fainted) Game.colors.hpLow else Game.colors.inkSoft,
                    modifier = Modifier.align(Alignment.End),
                )
                Spacer(Modifier.height(4.dp))
                StatBar("EXP", companion.expProgress, Game.colors.accent, height = 5.dp)
                Text(
                    if (companion.level >= GameRules.MAX_LEVEL) "MAX LEVEL" else "${companion.expToNext} EXP to Lv.${companion.level + 1}",
                    style = RetroType.Small,
                    color = Game.colors.inkSoft,
                    modifier = Modifier.align(Alignment.End),
                )
                Spacer(Modifier.height(8.dp))
                InfoLine("HABITAT", companion.habitat.title)
                InfoLine("MET", LocalDate.ofEpochDay(companion.metOnDay).toString())
                InfoLine("USED", "${companion.launches} times")
                GameRules.EVOLVE_LEVELS.firstOrNull { companion.level < it }?.let { InfoLine("EVOLVES", "at Lv.$it") }
            }

            RetroWindow(Modifier.fillMaxWidth(), padding = 10.dp) {
                Text("MOVES (${companion.apps.size})", style = RetroType.Body, color = Game.colors.ink)
                Spacer(Modifier.height(6.dp))
                if (companion.apps.isEmpty()) {
                    Text(
                        "No moves left. Move an app into the ${companion.habitat.title} from the BAG.",
                        style = RetroType.Small,
                        color = Game.colors.inkSoft,
                    )
                }
                companion.apps.forEach { app ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .pressable(onClick = { onUseMove(app) })
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        AppIcon(app, iconProvider, size = 28.dp)
                        Spacer(Modifier.width(8.dp))
                        Text(
                            app.label.uppercase(),
                            style = RetroType.Small,
                            color = Game.colors.ink,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
            }
        }
    }
}

/**
 * BOX。手持ちに入らなかった相棒と、まだ出会っていない住みか。
 * 出会っていない住みかを押すと、その住みかで絞った BAG が開く。
 */
@Composable
fun BoxScreen(
    ui: GameUi,
    onOpen: (Screen) -> Unit,
    onBack: () -> Unit,
) {
    SubScreen(title = "BOX", onBack = onBack) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(ui.box, key = { it.habitat.key }) { companion ->
                CompanionRow(
                    companion,
                    onClick = {
                        onOpen(if (companion.met) Screen.Summary(companion.habitat) else Screen.Bag(companion.habitat))
                    },
                )
            }
        }
        if (ui.box.isEmpty()) {
            RetroWindow(Modifier.fillMaxWidth(), padding = 12.dp) {
                Text("The BOX is empty.", style = RetroType.Body, color = Game.colors.ink)
            }
        }
    }
}

@Composable
private fun CompanionRow(companion: CompanionUi, onClick: () -> Unit) {
    RetroWindow(Modifier.fillMaxWidth(), padding = 8.dp, onClick = onClick) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            CompanionSprite(companion, size = 48.dp)
            Spacer(Modifier.width(10.dp))
            Column(Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        if (companion.met) companion.name else "???",
                        style = RetroType.Title.copy(fontSize = 13.sp),
                        color = Game.colors.ink,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f),
                    )
                    if (companion.met) Text("Lv.${companion.level}", style = RetroType.Body, color = Game.colors.ink)
                }
                HabitatTag(companion.habitat)
                if (companion.met) {
                    Spacer(Modifier.height(4.dp))
                    StatBar("HP", companion.hp, hpColor(companion.hp))
                } else {
                    Text(
                        when (companion.apps.size) {
                            0 -> "No apps live here yet."
                            1 -> "Use the 1 app here to meet it."
                            else -> "Use one of the ${companion.apps.size} apps here to meet it."
                        },
                        style = RetroType.Small,
                        color = Game.colors.inkSoft,
                    )
                }
            }
        }
    }
}
