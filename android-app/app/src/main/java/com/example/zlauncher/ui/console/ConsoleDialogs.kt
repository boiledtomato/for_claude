package com.example.zlauncher.ui.console

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.zlauncher.core.designsystem.ZColors
import com.example.zlauncher.core.designsystem.ZType
import com.example.zlauncher.domain.model.AppEntry
import com.example.zlauncher.ui.home.component.AppIconTile
import com.example.zlauncher.ui.home.component.rememberAppIcon

/** カテゴリーの新規作成・名前と色の変更 */
@Composable
fun CategoryEditDialog(
    title: String,
    initialName: String,
    initialColorIndex: Int,
    onDismiss: () -> Unit,
    onConfirm: (String, Int) -> Unit,
) {
    var name by remember { mutableStateOf(initialName) }
    var colorIndex by remember { mutableStateOf(initialColorIndex) }

    Dialog(onDismissRequest = onDismiss) {
        Column(
            Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(ZColors.Surface)
                .border(1.dp, ZColors.OutlineStrong, RoundedCornerShape(16.dp))
                .padding(18.dp),
        ) {
            Text(title, style = ZType.Title, color = ZColors.TextPrimary)
            Spacer(Modifier.height(14.dp))
            Box(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(ZColors.SurfaceLow)
                    .border(1.dp, ZColors.Outline, RoundedCornerShape(10.dp))
                    .padding(horizontal = 12.dp, vertical = 11.dp),
            ) {
                if (name.isEmpty()) {
                    Text("カテゴリー名", style = ZType.Body, color = ZColors.TextSecondary)
                }
                BasicTextField(
                    value = name,
                    onValueChange = { name = it },
                    singleLine = true,
                    textStyle = ZType.Body.copy(color = ZColors.TextPrimary),
                    cursorBrush = SolidColor(ZColors.AccentAlt),
                    modifier = Modifier.fillMaxWidth(),
                )
            }
            Spacer(Modifier.height(16.dp))
            Text("色", style = ZType.Eyebrow, color = ZColors.TextSecondary)
            Spacer(Modifier.height(10.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                ZColors.CategoryColors.forEachIndexed { index, color ->
                    Box(
                        Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                            .background(color.copy(alpha = if (index == colorIndex) 1f else 0.35f))
                            .border(
                                width = if (index == colorIndex) 2.dp else 1.dp,
                                color = if (index == colorIndex) ZColors.TextPrimary else ZColors.Outline,
                                shape = CircleShape,
                            )
                            .clickable { colorIndex = index }
                    )
                }
            }
            Spacer(Modifier.height(20.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                DialogButton("キャンセル", accent = false, onClick = onDismiss)
                Spacer(Modifier.size(10.dp))
                DialogButton("保存", accent = true) {
                    if (name.isNotBlank()) onConfirm(name.trim(), colorIndex)
                }
            }
        }
    }
}

/** アプリ選択。カテゴリーの中身にも、レールのピン留めにも使う */
@Composable
fun AppPickerDialog(
    title: String,
    apps: List<AppEntry>,
    initiallySelected: Set<String>,
    multiSelect: Boolean,
    iconProvider: suspend (AppEntry) -> ImageBitmap?,
    onDismiss: () -> Unit,
    onConfirm: (List<String>) -> Unit,
) {
    val selected = remember { initiallySelected.toMutableStateList() }
    var query by remember { mutableStateOf("") }
    val filtered = remember(apps, query) {
        if (query.isBlank()) apps else apps.filter { it.label.contains(query, ignoreCase = true) }
    }

    Dialog(onDismissRequest = onDismiss, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(ZColors.Background)
                .border(1.dp, ZColors.OutlineStrong, RoundedCornerShape(18.dp))
                .padding(16.dp),
        ) {
            Text(title, style = ZType.Title, color = ZColors.TextPrimary)
            Spacer(Modifier.height(12.dp))
            Box(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(ZColors.Surface)
                    .border(1.dp, ZColors.Outline, RoundedCornerShape(10.dp))
                    .padding(horizontal = 12.dp, vertical = 10.dp),
            ) {
                if (query.isEmpty()) {
                    Text("アプリを検索", style = ZType.Body, color = ZColors.TextSecondary)
                }
                BasicTextField(
                    value = query,
                    onValueChange = { query = it },
                    singleLine = true,
                    textStyle = ZType.Body.copy(color = ZColors.TextPrimary),
                    cursorBrush = SolidColor(ZColors.AccentAlt),
                    modifier = Modifier.fillMaxWidth(),
                )
            }
            Spacer(Modifier.height(10.dp))
            LazyColumn(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                items(filtered, key = { it.key }) { entry ->
                    val icon by rememberAppIcon(entry, iconProvider)
                    val isSelected = entry.packageName in selected
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (isSelected) ZColors.Accent.copy(alpha = 0.10f) else ZColors.Surface)
                            .border(
                                1.dp,
                                if (isSelected) ZColors.Accent.copy(alpha = 0.45f) else ZColors.Outline,
                                RoundedCornerShape(10.dp),
                            )
                            .clickable {
                                if (isSelected) {
                                    selected.remove(entry.packageName)
                                } else {
                                    if (!multiSelect) selected.clear()
                                    selected.add(entry.packageName)
                                }
                            }
                            .padding(horizontal = 10.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        AppIconTile(icon = icon, size = 36.dp, background = ZColors.SurfaceHigh)
                        Text(
                            text = entry.label,
                            style = ZType.Body,
                            color = ZColors.TextPrimary,
                            modifier = Modifier.weight(1f),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Text(
                            text = if (isSelected) "選択中" else "",
                            style = ZType.Sub,
                            color = ZColors.AccentSoft,
                        )
                    }
                }
            }
            Spacer(Modifier.height(12.dp))
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = if (multiSelect) "${selected.size} 個を選択中" else "",
                    style = ZType.Sub,
                    color = ZColors.TextSecondary,
                    modifier = Modifier.weight(1f),
                )
                DialogButton("キャンセル", accent = false, onClick = onDismiss)
                Spacer(Modifier.size(10.dp))
                DialogButton("決定", accent = true) { onConfirm(selected.toList()) }
            }
        }
    }
}

@Composable
private fun DialogButton(label: String, accent: Boolean, onClick: () -> Unit) {
    Box(
        Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(if (accent) ZColors.Accent.copy(alpha = 0.14f) else ZColors.SurfaceLow)
            .border(
                1.dp,
                if (accent) ZColors.Accent.copy(alpha = 0.5f) else ZColors.Outline,
                RoundedCornerShape(999.dp),
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 9.dp),
    ) {
        Text(
            label,
            style = ZType.Body.copy(fontSize = 12.5.sp),
            color = if (accent) ZColors.AccentSoft else ZColors.TextSecondary,
        )
    }
}
