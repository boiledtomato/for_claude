package com.example.critterhome.ui

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.critterhome.data.AppEntry
import com.example.critterhome.data.BatteryLevel
import com.example.critterhome.game.GameRules
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlinx.coroutines.delay

/** ホームに並べる最近のアプリの数。1 行に収まる範囲で */
private const val RECENT_ON_HOME = 5

/**
 * ホーム。上から 時計とトレーナー / 最近のアプリ / 手持ち / メニュー。
 *
 * アプリ一覧（BAG）はメニューから 1 タップ。最近開いたものはここから直接開ける ―
 * ゲームの画面であっても、ホームアプリとして毎日使える速さを先に置く。
 */
@Composable
fun HomeScreen(
    ui: GameUi,
    battery: BatteryLevel,
    iconProvider: suspend (AppEntry) -> Bitmap?,
    onLaunch: (AppEntry) -> Unit,
    onOpen: (Screen) -> Unit,
    onAdvanceMessage: () -> Unit,
) {
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        StatusWindow(ui, battery)

        if (ui.recent.isNotEmpty()) {
            RetroWindow(Modifier.fillMaxWidth(), padding = 8.dp) {
                Text("RECENT", style = RetroType.Small, color = Game.colors.inkDim)
                Spacer(Modifier.height(6.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    ui.recent.take(RECENT_ON_HOME).forEach { app ->
                        Column(
                            Modifier.width(56.dp).pressable(onClick = { onLaunch(app) }),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            AppIcon(app, iconProvider, size = 40.dp)
                            Text(
                                app.label,
                                style = RetroType.Small.copy(fontSize = 9.sp),
                                color = Game.colors.inkSoft,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }
                    }
                }
            }
        }

        RetroWindow(Modifier.fillMaxWidth().weight(1f), padding = 10.dp) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("PARTY", style = RetroType.Body, color = Game.colors.ink, modifier = Modifier.weight(1f))
                Text("${ui.party.size}/${GameRules.PARTY_SIZE}", style = RetroType.Small, color = Game.colors.inkDim)
            }
            Spacer(Modifier.height(8.dp))
            if (ui.loaded && ui.party.isEmpty()) {
                Text(
                    "No companions yet.\n\nOpen the BAG and use any app. The companion living in that app's habitat will come to meet you.",
                    style = RetroType.Body,
                    color = Game.colors.ink,
                )
            }
            Column(
                Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                ui.party.chunked(2).forEach { pair ->
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        pair.forEach { companion ->
                            PartyCard(
                                companion,
                                onClick = { onOpen(Screen.Summary(companion.habitat)) },
                                modifier = Modifier.weight(1f),
                            )
                        }
                        if (pair.size == 1) Spacer(Modifier.weight(1f))
                    }
                }
            }
        }

        ui.message?.let { text ->
            MessageBox(text = text, key = text to ui.messageCount, onAdvance = onAdvanceMessage)
        }

        RetroWindow(Modifier.fillMaxWidth(), padding = 4.dp) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                CursorItem("BAG", selected = true, onClick = { onOpen(Screen.Bag()) })
                CursorItem("BOX", selected = false, onClick = { onOpen(Screen.Box) })
                CursorItem("BADGES", selected = false, onClick = { onOpen(Screen.Badges) })
                CursorItem("CARD", selected = false, onClick = { onOpen(Screen.Card) })
            }
        }
    }
}

/** 時計とトレーナー。HP は端末の電池残量 */
@Composable
private fun StatusWindow(ui: GameUi, battery: BatteryLevel) {
    var now by remember { mutableLongStateOf(System.currentTimeMillis()) }
    LaunchedEffect(Unit) {
        while (true) {
            now = System.currentTimeMillis()
            // 次の分の頭まで待つ。毎秒描き直すほどの情報ではない
            delay(60_000 - now % 60_000)
        }
    }
    RetroWindow(Modifier.fillMaxWidth(), padding = 12.dp) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Text(
                    SimpleDateFormat("H:mm", Locale.getDefault()).format(Date(now)),
                    style = RetroType.Title.copy(fontSize = 34.sp, lineHeight = 38.sp),
                    color = Game.colors.ink,
                )
                Text(
                    SimpleDateFormat("EEE, MMM d", Locale.ENGLISH).format(Date(now)).uppercase(),
                    style = RetroType.Small,
                    color = Game.colors.inkSoft,
                )
            }
            Column(Modifier.width(132.dp)) {
                Text("TRAINER  Lv.${ui.trainerLevel}", style = RetroType.Body.copy(fontSize = 11.sp), color = Game.colors.ink)
                Spacer(Modifier.height(4.dp))
                val hp = battery.percent / 100f
                StatBar("HP", hp, hpColor(hp))
                Text(
                    if (battery.charging) "${battery.percent}% RESTING" else "${battery.percent}%",
                    style = RetroType.Small.copy(fontSize = 9.sp),
                    color = Game.colors.inkSoft,
                    modifier = Modifier.align(Alignment.End),
                )
                StatBar("EXP", ui.trainerProgress, Game.colors.accent, height = 4.dp)
            }
        }
    }
}

@Composable
private fun PartyCard(companion: CompanionUi, onClick: () -> Unit, modifier: Modifier = Modifier) {
    RetroWindow(modifier, padding = 6.dp, onClick = onClick) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            CompanionSprite(companion, size = 36.dp)
            Spacer(Modifier.width(6.dp))
            Column(Modifier.weight(1f)) {
                Text(
                    companion.name,
                    style = RetroType.Body.copy(fontSize = 11.sp),
                    color = Game.colors.ink,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    if (companion.fainted) "Lv.${companion.level} FNT" else "Lv.${companion.level}",
                    style = RetroType.Small.copy(fontSize = 9.sp),
                    color = if (companion.fainted) Game.colors.hpLow else Game.colors.inkSoft,
                )
                Spacer(Modifier.height(2.dp))
                StatBar("HP", companion.hp, hpColor(companion.hp), height = 5.dp)
            }
        }
    }
}
