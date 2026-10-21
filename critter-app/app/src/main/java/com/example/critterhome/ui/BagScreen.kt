package com.example.critterhome.ui

import android.graphics.Bitmap
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.critterhome.data.AppEntry
import com.example.critterhome.game.Habitat

/**
 * BAG ＝ 全アプリの一覧。名前で探せて、住みかで絞れる。
 *
 * 押すと起動、長押しでそのアプリのメニュー（住みかを移す / App info）。
 * 住みかを移すのは、自動の振り分けが外れたときの逃げ道。
 */
@Composable
fun BagScreen(
    ui: GameUi,
    initialFilter: Habitat?,
    iconProvider: suspend (AppEntry) -> Bitmap?,
    onLaunch: (AppEntry) -> Unit,
    onAppInfo: (AppEntry) -> Unit,
    onMove: (AppEntry, Habitat) -> Unit,
    onBack: () -> Unit,
) {
    var query by rememberSaveable { mutableStateOf("") }
    var filterKey by rememberSaveable { mutableStateOf(initialFilter?.key) }
    val filter = filterKey?.let(Habitat::ofKey)
    var menuFor by remember { mutableStateOf<AppEntry?>(null) }

    val shown = remember(ui.apps, ui.habitatOf, query, filter) {
        val q = query.trim().lowercase()
        ui.apps.filter { app ->
            (filter == null || ui.habitatOf[app.key] == filter) &&
                (q.isEmpty() || app.label.lowercase().contains(q) || app.packageName.lowercase().contains(q))
        }
    }

    SubScreen(title = "BAG", onBack = onBack) {
        RetroWindow(Modifier.fillMaxWidth(), padding = 10.dp) {
            Box {
                if (query.isEmpty()) {
                    Text("SEARCH…", style = RetroType.Body, color = Game.colors.inkDim)
                }
                BasicTextField(
                    value = query,
                    onValueChange = { query = it },
                    singleLine = true,
                    textStyle = RetroType.Body.copy(color = Game.colors.ink),
                    cursorBrush = SolidColor(Game.colors.ink),
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
        Row(Modifier.horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            CursorItem("ALL", selected = filter == null, onClick = { filterKey = null })
            Habitat.entries.forEach { h ->
                CursorItem(h.title.uppercase(), selected = filter == h, onClick = { filterKey = h.key })
            }
        }
        RetroWindow(Modifier.fillMaxWidth().weight(1f), padding = 6.dp) {
            if (shown.isEmpty()) {
                Text(
                    if (ui.apps.isEmpty()) "Looking through the BAG…" else "Nothing like that in the BAG.",
                    style = RetroType.Body,
                    color = Game.colors.inkSoft,
                    modifier = Modifier.padding(6.dp),
                )
            }
            LazyColumn(contentPadding = PaddingValues(vertical = 2.dp)) {
                items(shown, key = { it.key }) { app ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .pressable(onLongClick = { menuFor = app }, onClick = { onLaunch(app) })
                            .padding(horizontal = 4.dp, vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        AppIcon(app, iconProvider, size = 36.dp)
                        Spacer(Modifier.width(10.dp))
                        Column(Modifier.weight(1f)) {
                            Text(
                                app.label,
                                style = RetroType.Body,
                                color = Game.colors.ink,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                ui.habitatOf[app.key]?.let { HabitatTag(it) }
                                if (app.isWorkProfile) {
                                    Spacer(Modifier.width(6.dp))
                                    Text("WORK", style = RetroType.Small.copy(fontSize = 9.sp), color = Game.colors.accent)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    menuFor?.let { app ->
        AppMenu(
            app = app,
            current = ui.habitatOf[app.key],
            onDismiss = { menuFor = null },
            onLaunch = { onLaunch(app); menuFor = null },
            onAppInfo = { onAppInfo(app); menuFor = null },
            onMove = { h -> onMove(app, h); menuFor = null },
        )
    }
}

@Composable
private fun AppMenu(
    app: AppEntry,
    current: Habitat?,
    onDismiss: () -> Unit,
    onLaunch: () -> Unit,
    onAppInfo: () -> Unit,
    onMove: (Habitat) -> Unit,
) {
    var moving by remember { mutableStateOf(false) }
    Dialog(onDismissRequest = onDismiss) {
        // 狭い窓（分割画面）でも全部に届くよう、中身は巻き取れるようにする
        RetroWindow(Modifier.fillMaxWidth().heightIn(max = 520.dp), padding = 12.dp) {
            Text(app.label, style = RetroType.Title, color = Game.colors.ink, maxLines = 2, overflow = TextOverflow.Ellipsis)
            Spacer(Modifier.height(8.dp))
            Column(Modifier.verticalScroll(rememberScrollState())) {
                if (!moving) {
                    CursorItem("USE", selected = true, onClick = onLaunch)
                    CursorItem("MOVE HABITAT", selected = false, onClick = { moving = true })
                    CursorItem("APP INFO", selected = false, onClick = onAppInfo)
                    CursorItem("CANCEL", selected = false, onClick = onDismiss)
                } else {
                    Text("Move to which habitat?", style = RetroType.Small, color = Game.colors.inkSoft)
                    Spacer(Modifier.height(4.dp))
                    Habitat.entries.forEach { h ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            CursorItem(h.title.uppercase(), selected = h == current, onClick = { onMove(h) })
                        }
                    }
                    CursorItem("CANCEL", selected = false, onClick = { moving = false })
                }
            }
        }
    }
}
