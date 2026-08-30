package com.example.zlauncher.ui.console

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.zlauncher.core.designsystem.ZColors
import com.example.zlauncher.core.designsystem.ZMotion
import com.example.zlauncher.core.designsystem.ZType
import com.example.zlauncher.core.ui.springyClick
import com.example.zlauncher.domain.model.UrlCategoryEntry
import com.example.zlauncher.domain.model.UrlCategoryGroup

/**
 * カテゴリーの追加。
 *
 * Zscaler の定義済み URL カテゴリーを、**大項目を見出しにした折りたたみ**で並べる。
 * 選ぶ単位は小項目。大項目は 30 前後・小項目は 110 以上あるので、全部平置きすると
 * 目的のものに辿り着けない。
 *
 * ATP（Advanced Security）は取り込み時点で除いてあるので、ここには出てこない。
 */
@Composable
fun CategoryCatalogDialog(
    groups: List<UrlCategoryGroup>,
    existingKeys: Set<String>,
    existingNames: Set<String>,
    defaultColorIndex: Int,
    revisionLabel: String,
    onDismiss: () -> Unit,
    onConfirm: (entries: List<UrlCategoryEntry>, customName: String, customColorIndex: Int) -> Unit,
) {
    val selected = remember { mutableStateListOf<UrlCategoryEntry>() }
    val expanded = remember { mutableStateListOf<String>() }
    var query by remember { mutableStateOf("") }
    var customName by remember { mutableStateOf("") }
    var customColor by remember { mutableIntStateOf(defaultColorIndex) }

    val needle = query.trim().lowercase()
    val visible = remember(groups, needle) {
        if (needle.isEmpty()) {
            groups
        } else {
            groups.mapNotNull { group ->
                // 大項目名が当たったら中身を全部残す。当たらなければ小項目で絞る
                if (group.superCategory.lowercase().contains(needle)) {
                    group
                } else {
                    val hits = group.entries.filter {
                        it.category.lowercase().contains(needle) ||
                            it.description.lowercase().contains(needle)
                    }
                    if (hits.isEmpty()) null else group.copy(entries = hits)
                }
            }
        }
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
            Text("Add category", style = ZType.Title, color = ZColors.TextPrimary)
            Spacer(Modifier.height(4.dp))
            Text(
                "Zscaler predefined URL categories · $revisionLabel",
                style = ZType.Sub,
                color = ZColors.TextDim,
            )

            Spacer(Modifier.height(12.dp))
            TextInput(
                value = query,
                placeholder = "Search categories",
                onValueChange = { query = it },
            )

            Spacer(Modifier.height(10.dp))
            LazyColumn(
                Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                items(visible, key = { it.superCategory }) { group ->
                    // 検索中は開いた状態で見せる。閉じたままだと当たりが見えない
                    val isOpen = needle.isNotEmpty() || group.superCategory in expanded
                    SuperCategorySection(
                        group = group,
                        expanded = isOpen,
                        selected = selected,
                        existingKeys = existingKeys,
                        onToggleExpand = {
                            if (group.superCategory in expanded) expanded.remove(group.superCategory)
                            else expanded.add(group.superCategory)
                        },
                        onToggleEntry = { entry ->
                            val hit = selected.firstOrNull { it.key == entry.key }
                            if (hit != null) selected.remove(hit) else selected.add(entry)
                        },
                        modifier = Modifier.animateItem(placementSpec = ZMotion.placement()),
                    )
                }

                if (visible.isEmpty()) {
                    item(key = "no-match") {
                        Text(
                            "No category matches “$query”.",
                            style = ZType.Body,
                            color = ZColors.TextSecondary,
                            modifier = Modifier.padding(vertical = 16.dp),
                        )
                    }
                }

                item(key = "custom") {
                    Spacer(Modifier.height(14.dp))
                    Text("Custom", style = ZType.Eyebrow, color = ZColors.TextSecondary)
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "Not in the catalog — name it yourself",
                        style = ZType.Sub,
                        color = ZColors.TextDim,
                    )
                    Spacer(Modifier.height(10.dp))
                    TextInput(
                        value = customName,
                        placeholder = "Category name",
                        onValueChange = { customName = it },
                    )
                    Spacer(Modifier.height(10.dp))
                    ColorPicker(selected = customColor, onSelect = { customColor = it })
                }
            }

            Spacer(Modifier.height(12.dp))
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Text(
                    text = when {
                        selected.isEmpty() && customName.isBlank() -> "Nothing selected"
                        selected.isEmpty() -> "1 custom category"
                        customName.isBlank() -> "${selected.size} selected"
                        else -> "${selected.size} selected + 1 custom"
                    },
                    style = ZType.Sub,
                    color = ZColors.TextSecondary,
                    modifier = Modifier.weight(1f),
                )
                DialogButton("Cancel", accent = false, onClick = onDismiss)
                DialogButton(
                    label = "Add",
                    accent = true,
                    enabled = selected.isNotEmpty() || customName.isNotBlank(),
                    onClick = {
                        val name = customName.trim()
                        onConfirm(
                            selected.toList(),
                            if (name.isNotEmpty() && name !in existingNames) name else "",
                            customColor,
                        )
                    },
                )
            }
        }
    }
}

@Composable
private fun SuperCategorySection(
    group: UrlCategoryGroup,
    expanded: Boolean,
    selected: List<UrlCategoryEntry>,
    existingKeys: Set<String>,
    onToggleExpand: () -> Unit,
    onToggleEntry: (UrlCategoryEntry) -> Unit,
    modifier: Modifier = Modifier,
) {
    val selectedCount = group.entries.count { entry -> selected.any { it.key == entry.key } }
    val addedCount = group.entries.count { it.key in existingKeys }
    val rotation by animateFloatAsState(
        targetValue = if (expanded) 90f else 0f,
        animationSpec = ZMotion.touch(),
        label = "chevron",
    )
    val background by animateColorAsState(
        targetValue = if (expanded) ZColors.SurfaceHigh else ZColors.Surface,
        animationSpec = ZMotion.value(),
        label = "sectionBackground",
    )

    Column(
        modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(background)
            .border(1.dp, ZColors.Outline, RoundedCornerShape(12.dp)),
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .springyClick(onClick = onToggleExpand)
                .padding(horizontal = 12.dp, vertical = 11.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(9.dp),
        ) {
            Text(
                "›",
                style = ZType.Title.copy(fontSize = 16.sp),
                color = ZColors.TextSecondary,
                modifier = Modifier.rotate(rotation),
            )
            Column(Modifier.weight(1f)) {
                Text(
                    group.superCategory,
                    style = ZType.Body,
                    color = ZColors.TextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = buildString {
                        append("${group.entries.size} categories")
                        if (group.className.isNotBlank()) append(" · ${group.className}")
                    },
                    style = ZType.Sub,
                    color = ZColors.TextDim,
                )
            }
            if (selectedCount > 0) CountBadge("$selectedCount", accent = true)
            else if (addedCount > 0) CountBadge("$addedCount added", accent = false)
        }

        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically(),
        ) {
            Column(Modifier.padding(start = 12.dp, end = 12.dp, bottom = 10.dp)) {
                group.entries.forEach { entry ->
                    SubCategoryRow(
                        entry = entry,
                        selected = selected.any { it.key == entry.key },
                        already = entry.key in existingKeys,
                        onClick = { onToggleEntry(entry) },
                    )
                }
            }
        }
    }
}

@Composable
private fun SubCategoryRow(
    entry: UrlCategoryEntry,
    selected: Boolean,
    already: Boolean,
    onClick: () -> Unit,
) {
    val accent = ZColors.AccentSoft
    Row(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(if (selected) ZColors.Accent.copy(alpha = 0.12f) else ZColors.SurfaceLow)
            .border(
                1.dp,
                if (selected) ZColors.Accent.copy(alpha = 0.45f) else ZColors.Outline,
                RoundedCornerShape(8.dp),
            )
            .springyClick(enabled = !already, onClick = onClick)
            .padding(horizontal = 10.dp, vertical = 9.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(9.dp),
    ) {
        Box(
            Modifier
                .size(14.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(if (selected) ZColors.Accent else ZColors.StatusNeutral)
                .border(
                    1.dp,
                    if (already) ZColors.Outline else ZColors.OutlineStrong,
                    RoundedCornerShape(4.dp),
                ),
            contentAlignment = Alignment.Center,
        ) {
            if (selected) Text("✓", style = ZType.Sub.copy(fontSize = 9.sp), color = ZColors.TextPrimary)
        }
        Column(Modifier.weight(1f)) {
            Text(
                text = entry.category,
                style = ZType.Body,
                color = when {
                    already -> ZColors.TextDim
                    selected -> ZColors.TextPrimary
                    else -> ZColors.TextSecondary
                },
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            if (entry.description.isNotBlank()) {
                Text(
                    text = entry.description,
                    style = ZType.Sub,
                    color = ZColors.TextDim,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
        if (already) Text("Added", style = ZType.Sub.copy(fontSize = 9.sp), color = accent)
    }
    Spacer(Modifier.height(6.dp))
}

@Composable
private fun CountBadge(label: String, accent: Boolean) {
    Box(
        Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(if (accent) ZColors.Accent.copy(alpha = 0.16f) else ZColors.SurfaceLow)
            .border(
                1.dp,
                if (accent) ZColors.Accent.copy(alpha = 0.45f) else ZColors.Outline,
                RoundedCornerShape(999.dp),
            )
            .padding(horizontal = 8.dp, vertical = 3.dp),
    ) {
        Text(
            label,
            style = ZType.Sub.copy(fontSize = 10.sp),
            color = if (accent) ZColors.AccentSoft else ZColors.TextDim,
        )
    }
}

@Composable
private fun TextInput(value: String, placeholder: String, onValueChange: (String) -> Unit) {
    Box(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(ZColors.Surface)
            .border(1.dp, ZColors.Outline, RoundedCornerShape(10.dp))
            .padding(horizontal = 12.dp, vertical = 11.dp),
    ) {
        if (value.isEmpty()) {
            Text(placeholder, style = ZType.Body, color = ZColors.TextSecondary)
        }
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            textStyle = ZType.Body.copy(color = ZColors.TextPrimary),
            cursorBrush = SolidColor(ZColors.AccentAlt),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
private fun ColorPicker(selected: Int, onSelect: (Int) -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        ZColors.CategoryColors.forEachIndexed { index, color ->
            Box(
                Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(color.copy(alpha = if (index == selected) 1f else 0.35f))
                    .border(
                        2.dp,
                        if (index == selected) ZColors.TextPrimary else ZColors.Outline,
                        CircleShape,
                    )
                    .springyClick { onSelect(index) }
            )
        }
    }
}

@Composable
private fun DialogButton(
    label: String,
    accent: Boolean,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Box(
        Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(
                when {
                    !enabled -> ZColors.SurfaceLow
                    accent -> ZColors.Accent.copy(alpha = 0.16f)
                    else -> ZColors.Surface
                }
            )
            .border(
                1.dp,
                when {
                    !enabled -> ZColors.Outline
                    accent -> ZColors.Accent.copy(alpha = 0.5f)
                    else -> ZColors.Outline
                },
                RoundedCornerShape(999.dp),
            )
            .springyClick(enabled = enabled, onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
        Text(
            label,
            style = ZType.Body,
            color = when {
                !enabled -> ZColors.TextDim
                accent -> ZColors.AccentSoft
                else -> ZColors.TextSecondary
            },
        )
    }
}
