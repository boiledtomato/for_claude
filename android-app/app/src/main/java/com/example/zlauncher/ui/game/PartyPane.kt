package com.example.zlauncher.ui.game

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.zlauncher.core.designsystem.ZColors
import com.example.zlauncher.core.ui.springyClick
import com.example.zlauncher.domain.model.AppEntry
import com.example.zlauncher.domain.model.game.GameRules
import com.example.zlauncher.domain.model.game.MonsterSprite
import com.example.zlauncher.ui.apps.component.AppIconTile
import com.example.zlauncher.ui.apps.component.rememberAppIcon
import java.time.LocalDate
import kotlin.math.ceil

private enum class GameTab(val label: String) {
    PARTY("PARTY"),
    BOX("BOX"),
    BADGES("BADGES"),
    TRAINER("TRAINER"),
}

/** わざとして並べるアプリの数 */
private const val MOVE_COUNT = 4

/**
 * 相棒たちのペイン。カテゴリーごとに 1 体、そのカテゴリーのアプリを開くと育つ。
 *
 * 詳細（ステータス画面）では、そのカテゴリーのアプリを「わざ」として 4 つまで並べ、
 * 押すとそのまま起動する ― 遊ぶ操作とランチャーとして使う操作を分けない。
 */
@Composable
fun PartyPane(
    onOpenCategory: (String) -> Unit,
    viewModel: GameViewModel = hiltViewModel(),
) {
    val ui by viewModel.ui.collectAsStateWithLifecycle()
    val battery by viewModel.battery.collectAsStateWithLifecycle()
    var tab by rememberSaveable { mutableStateOf(GameTab.PARTY) }
    var detailId by rememberSaveable { mutableStateOf<String?>(null) }
    val detail = detailId?.let { id -> (ui.party + ui.box).firstOrNull { it.categoryId == id && it.met } }

    // 詳細から戻るのが先。コンソール側の「Overview へ戻る」より内側で拾う
    BackHandler(enabled = detail != null) { detailId = null }

    Column(Modifier.fillMaxSize().padding(start = 12.dp, end = 16.dp, bottom = 12.dp)) {
        Box(Modifier.weight(1f).fillMaxWidth()) {
            when {
                !ui.loaded -> Unit
                detail != null -> MonsterSummary(
                    monster = detail,
                    iconProvider = viewModel::icon,
                    onBack = { detailId = null },
                    onUseMove = viewModel::launch,
                    onOpenCategory = onOpenCategory,
                )
                else -> Column(Modifier.fillMaxSize()) {
                    RetroWindow(Modifier.fillMaxWidth(), padding = 4.dp) {
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            GameTab.entries.forEach { entry ->
                                CursorItem(entry.label, selected = tab == entry, onClick = { tab = entry })
                            }
                        }
                    }
                    Spacer(Modifier.height(10.dp))
                    when (tab) {
                        GameTab.PARTY -> MonsterList(
                            monsters = ui.party,
                            empty = if (ui.hasCategories) {
                                "Your party is empty.\nOpen an app from any category to meet its companion."
                            } else {
                                "No companions yet.\nEvery category on the rail hatches one. Add a category, then open one of its apps."
                            },
                            onSelect = { detailId = it.categoryId },
                            onUnmet = { onOpenCategory(it.categoryId) },
                        )
                        GameTab.BOX -> MonsterList(
                            monsters = ui.box,
                            empty = "The BOX is empty.\nCompanions past the first ${GameRules.PARTY_SIZE} rest here.",
                            onSelect = { detailId = it.categoryId },
                            onUnmet = { onOpenCategory(it.categoryId) },
                        )
                        GameTab.BADGES -> BadgeList(ui.badges)
                        GameTab.TRAINER -> TrainerCard(ui, battery.first, battery.second)
                    }
                }
            }
        }
        ui.message?.let { text ->
            Spacer(Modifier.height(10.dp))
            MessageBox(text = text, key = text to ui.launches, onAdvance = viewModel::consumeMessage)
        }
    }
}

@Composable
private fun MonsterList(
    monsters: List<MonsterUi>,
    empty: String,
    onSelect: (MonsterUi) -> Unit,
    onUnmet: (MonsterUi) -> Unit,
) {
    if (monsters.isEmpty()) {
        RetroWindow(Modifier.fillMaxWidth(), padding = 12.dp) {
            Text(empty, style = RetroType.Body, color = ZColors.TextPrimary)
        }
        return
    }
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(bottom = 4.dp),
    ) {
        items(monsters, key = { it.categoryId }) { monster ->
            MonsterRow(monster, onClick = { if (monster.met) onSelect(monster) else onUnmet(monster) })
        }
    }
}

@Composable
private fun MonsterRow(monster: MonsterUi, onClick: () -> Unit) {
    val color = ZColors.CategoryColors[monster.colorIndex % ZColors.CategoryColors.size]
    val grid = remember(monster.categoryId, monster.stage) {
        MonsterSprite.pixels(monster.categoryId, monster.stage)
    }
    RetroWindow(Modifier.fillMaxWidth(), padding = 8.dp, onClick = onClick) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            PixelSprite(
                grid = grid,
                color = color,
                size = 48.dp,
                silhouette = !monster.met,
                fainted = monster.fainted,
            )
            Spacer(Modifier.width(10.dp))
            Column(Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        if (monster.met) monster.name else "???",
                        style = RetroType.Title.copy(fontSize = 13.sp),
                        color = ZColors.TextPrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f),
                    )
                    if (monster.met) {
                        Text("Lv.${monster.level}", style = RetroType.Body, color = ZColors.TextPrimary)
                    }
                }
                if (monster.met) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            monster.type,
                            style = RetroType.Small,
                            color = color,
                        )
                        if (monster.fainted) {
                            Spacer(Modifier.width(6.dp))
                            Text("FNT", style = RetroType.Small, color = ZColors.StatusRed)
                        }
                        Spacer(Modifier.width(6.dp))
                        Text(
                            monster.categoryName,
                            style = RetroType.Small,
                            color = ZColors.TextDim,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                    Spacer(Modifier.height(4.dp))
                    StatBar("HP", monster.hp, hpColor(monster.hp))
                    Spacer(Modifier.height(3.dp))
                    StatBar("EXP", monster.expProgress, ZColors.Accent, height = 4.dp)
                } else {
                    Text(
                        if (monster.apps.isEmpty()) {
                            "Add apps to “${monster.categoryName}” first"
                        } else {
                            "Open an app in “${monster.categoryName}” to meet it"
                        },
                        style = RetroType.Small,
                        color = ZColors.TextSecondary,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}

/** ステータス画面。上に姿と数値、下にわざ（＝ アプリ） */
@Composable
private fun MonsterSummary(
    monster: MonsterUi,
    iconProvider: suspend (AppEntry) -> ImageBitmap?,
    onBack: () -> Unit,
    onUseMove: (AppEntry) -> Unit,
    onOpenCategory: (String) -> Unit,
) {
    val color = ZColors.CategoryColors[monster.colorIndex % ZColors.CategoryColors.size]
    val grid = remember(monster.categoryId, monster.stage) {
        MonsterSprite.pixels(monster.categoryId, monster.stage)
    }
    Column(
        Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        CursorItem("BACK", selected = true, onClick = onBack)
        RetroWindow(Modifier.fillMaxWidth(), padding = 12.dp) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                PixelSprite(grid = grid, color = color, size = 104.dp, fainted = monster.fainted)
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text(monster.name, style = RetroType.Title, color = ZColors.TextPrimary, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    Text("Lv.${monster.level}", style = RetroType.Body, color = ZColors.TextPrimary)
                    Spacer(Modifier.height(4.dp))
                    Text("TYPE/${monster.type}", style = RetroType.Small, color = color)
                    Text(
                        "STAGE ${monster.stage + 1}/3",
                        style = RetroType.Small,
                        color = ZColors.TextSecondary,
                    )
                }
            }
            Spacer(Modifier.height(10.dp))
            val hpDays = ceil(monster.hp * GameRules.HP_DAYS).toInt()
            StatBar("HP", monster.hp, hpColor(monster.hp))
            Text(
                if (monster.fainted) "Fainted. Use any of its moves to revive it." else "HP $hpDays/${GameRules.HP_DAYS}",
                style = RetroType.Small,
                color = if (monster.fainted) ZColors.StatusRed else ZColors.TextSecondary,
                modifier = Modifier.align(Alignment.End),
            )
            Spacer(Modifier.height(4.dp))
            StatBar("EXP", monster.expProgress, ZColors.Accent, height = 5.dp)
            Text(
                if (monster.level >= GameRules.MAX_LEVEL) "MAX LEVEL" else "${monster.expToNext} EXP to Lv.${monster.level + 1}",
                style = RetroType.Small,
                color = ZColors.TextSecondary,
                modifier = Modifier.align(Alignment.End),
            )
            Spacer(Modifier.height(8.dp))
            InfoLine("HOME", monster.categoryName)
            InfoLine("MET", LocalDate.ofEpochDay(monster.metOnDay).toString())
            InfoLine("USED", "${monster.launches} times")
            nextEvolution(monster.level)?.let { InfoLine("EVOLVES", "at Lv.$it") }
        }

        RetroWindow(Modifier.fillMaxWidth(), padding = 10.dp) {
            Text("MOVES", style = RetroType.Body, color = ZColors.TextPrimary)
            Spacer(Modifier.height(6.dp))
            val moves = monster.apps.take(MOVE_COUNT)
            if (moves.isEmpty()) {
                Text("No moves yet. Add apps to “${monster.categoryName}”.", style = RetroType.Small, color = ZColors.TextSecondary)
            }
            moves.chunked(2).forEach { pair ->
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    pair.forEach { app ->
                        MoveButton(app, iconProvider, onClick = { onUseMove(app) }, modifier = Modifier.weight(1f))
                    }
                    if (pair.size == 1) Spacer(Modifier.weight(1f))
                }
                Spacer(Modifier.height(6.dp))
            }
            val more = monster.apps.size - moves.size
            if (more > 0) {
                CursorItem("+$more more in ${monster.categoryName}", selected = false, onClick = { onOpenCategory(monster.categoryId) })
            }
        }
    }
}

private fun nextEvolution(level: Int): Int? = GameRules.EVOLVE_LEVELS.firstOrNull { level < it }

@Composable
private fun InfoLine(label: String, value: String) {
    Row(Modifier.fillMaxWidth().padding(vertical = 1.dp)) {
        Text(label, style = RetroType.Small, color = ZColors.TextDim, modifier = Modifier.width(64.dp))
        Text(value, style = RetroType.Small, color = ZColors.TextPrimary, maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
}

@Composable
private fun MoveButton(
    app: AppEntry,
    iconProvider: suspend (AppEntry) -> ImageBitmap?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val icon by rememberAppIcon(app, iconProvider)
    Row(
        modifier
            .springyClick(onClick = onClick)
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AppIconTile(icon = icon, size = 28.dp)
        Spacer(Modifier.width(6.dp))
        Text(
            app.label.uppercase(),
            style = RetroType.Small,
            color = ZColors.TextPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
private fun BadgeList(badges: List<BadgeUi>) {
    val earned = badges.count { it.earned }
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
        RetroWindow(Modifier.fillMaxWidth(), padding = 10.dp) {
            Text("BADGES $earned/${badges.size}", style = RetroType.Body, color = ZColors.TextPrimary)
            Spacer(Modifier.height(8.dp))
            badges.forEachIndexed { index, item ->
                val color = ZColors.CategoryColors[(index * 2) % ZColors.CategoryColors.size]
                Row(Modifier.fillMaxWidth().padding(vertical = 5.dp), verticalAlignment = Alignment.CenterVertically) {
                    BadgeEmblem(color = color, earned = item.earned, size = 30.dp)
                    Spacer(Modifier.width(10.dp))
                    Column(Modifier.weight(1f)) {
                        Text(
                            item.badge.name.uppercase(),
                            style = RetroType.Body.copy(fontSize = 11.sp),
                            color = if (item.earned) ZColors.TextPrimary else ZColors.TextDim,
                        )
                        Text(item.badge.description, style = RetroType.Small, color = ZColors.TextSecondary)
                    }
                }
            }
        }
    }
}

@Composable
private fun TrainerCard(ui: GameUi, batteryPercent: Int, charging: Boolean) {
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
        RetroWindow(Modifier.fillMaxWidth(), padding = 12.dp) {
            Text("TRAINER CARD", style = RetroType.Title, color = ZColors.TextPrimary)
            Spacer(Modifier.height(8.dp))
            InfoLine("LEVEL", "Lv.${ui.trainerLevel}")
            StatBar("EXP", ui.trainerProgress, ZColors.Accent, height = 5.dp)
            Spacer(Modifier.height(8.dp))
            // 端末の電池がトレーナーの体力。ゲームの中でここだけは実測値
            val hp = batteryPercent / 100f
            StatBar("HP", hp, hpColor(hp))
            Text(
                if (charging) "$batteryPercent% · resting (charging)" else "$batteryPercent% · battery",
                style = RetroType.Small,
                color = ZColors.TextSecondary,
                modifier = Modifier.align(Alignment.End),
            )
            Spacer(Modifier.height(8.dp))
            InfoLine("OPENED", "${ui.launches} apps")
            InfoLine("STREAK", "${ui.streakDays} days (best ${ui.bestStreak})")
            InfoLine("MET", "${(ui.party + ui.box).count { it.met }} companions")
            InfoLine("BADGES", "${ui.badges.count { it.earned }}/${ui.badges.size}")
            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                ui.badges.forEachIndexed { index, item ->
                    BadgeEmblem(
                        color = ZColors.CategoryColors[(index * 2) % ZColors.CategoryColors.size],
                        earned = item.earned,
                        size = 22.dp,
                    )
                }
            }
        }
    }
}
