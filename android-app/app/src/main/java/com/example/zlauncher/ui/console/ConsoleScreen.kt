package com.example.zlauncher.ui.console

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.zlauncher.core.designsystem.ZColors
import com.example.zlauncher.core.designsystem.ZType
import com.example.zlauncher.data.apps.CategoryWithApps
import com.example.zlauncher.domain.model.AppEntry
import com.example.zlauncher.ui.home.component.AppIconTile
import com.example.zlauncher.ui.home.component.rememberAppIcon
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private val RAIL_WIDTH = 84.dp

@Composable
fun ConsoleScreen(
    onBack: () -> Unit,
    onAddWidget: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ConsoleViewModel = hiltViewModel(),
) {
    val snapshot by viewModel.snapshot.collectAsStateWithLifecycle()
    val categories by viewModel.categories.collectAsStateWithLifecycle()
    val pinned by viewModel.pinnedApps.collectAsStateWithLifecycle()
    val allApps by viewModel.allApps.collectAsStateWithLifecycle()

    var showCreateDialog by remember { mutableStateOf(false) }
    var editingCategory by remember { mutableStateOf<CategoryWithApps?>(null) }
    var pickingAppsFor by remember { mutableStateOf<CategoryWithApps?>(null) }
    var pinningSlot by remember { mutableStateOf<Int?>(null) }

    val selected = viewModel.pane
    val selectedCategory = (selected as? ConsolePane.Category)?.let { pane ->
        categories.firstOrNull { it.id == pane.id }
    }

    Row(modifier.fillMaxSize().background(ZColors.Background)) {
        ConsoleRail(
            pinned = pinned,
            pinnedSlots = viewModel.pinnedSlots,
            categories = categories,
            selected = selected,
            iconProvider = viewModel::icon,
            onSelect = viewModel::select,
            onLaunchPinned = { viewModel.launch(it) },
            onEditPin = { slot -> pinningSlot = slot },
            onAddCategory = { showCreateDialog = true },
        )

        Column(
            Modifier
                .fillMaxSize()
                .padding(WindowInsets.safeDrawing.asPaddingValues()),
        ) {
            ConsoleTopBar(
                title = selectedCategory?.category?.name ?: "概要",
                subtitle = if (selectedCategory != null) {
                    "${selectedCategory.apps.size} 個のアプリ"
                } else {
                    "ライブ · ${formatClock(snapshot.metrics.sampledAtMillis)} 更新"
                },
                live = selectedCategory == null && snapshot.loaded,
                isEditing = viewModel.isEditing,
                showEdit = selectedCategory == null,
                onBack = onBack,
                onToggleEdit = { viewModel.setEditMode(!viewModel.isEditing) },
            )

            if (selectedCategory != null) {
                CategoryPane(
                    category = selectedCategory,
                    iconProvider = viewModel::icon,
                    onLaunch = { entry -> viewModel.launch(entry) },
                    onRemoveApp = { pkg -> viewModel.removeAppFromCategory(selectedCategory.id, pkg) },
                    onPickApps = { pickingAppsFor = selectedCategory },
                    onEditCategory = { editingCategory = selectedCategory },
                    onDeleteCategory = { viewModel.deleteCategory(selectedCategory.id) },
                )
            } else {
                OverviewPane(
                    viewModel = viewModel,
                    snapshot = snapshot,
                    onAddWidget = onAddWidget,
                )
            }
        }
    }

    if (showCreateDialog) {
        CategoryCreateDialog(
            existingNames = categories.map { it.category.name }.toSet(),
            defaultColorIndex = categories.size % ZColors.CategoryColors.size,
            onDismiss = { showCreateDialog = false },
            onConfirm = { presets, customName, customColor ->
                if (presets.isNotEmpty()) {
                    viewModel.createFromPresets(presets.map { it.name to it.colorIndex })
                }
                if (customName.isNotBlank()) viewModel.createCategory(customName, customColor)
                showCreateDialog = false
            },
        )
    }

    editingCategory?.let { target ->
        CategoryEditDialog(
            title = "カテゴリーを編集",
            initialName = target.category.name,
            initialColorIndex = target.category.colorIndex,
            onDismiss = { editingCategory = null },
            onConfirm = { name, color ->
                viewModel.renameCategory(target.id, name)
                viewModel.setCategoryColor(target.id, color)
                editingCategory = null
            },
        )
    }

    pickingAppsFor?.let { target ->
        AppPickerDialog(
            title = "「${target.category.name}」に入れるアプリ",
            apps = allApps,
            initiallySelected = target.category.packages.toSet(),
            multiSelect = true,
            iconProvider = viewModel::icon,
            onDismiss = { pickingAppsFor = null },
            onConfirm = { packages ->
                viewModel.setCategoryApps(target.id, packages)
                pickingAppsFor = null
            },
        )
    }

    pinningSlot?.let { slot ->
        AppPickerDialog(
            title = "ピン留めするアプリ（${slot + 1} 枠目）",
            apps = allApps,
            initiallySelected = pinned.getOrNull(slot)?.let { setOf(it.packageName) } ?: emptySet(),
            multiSelect = false,
            iconProvider = viewModel::icon,
            onDismiss = { pinningSlot = null },
            onConfirm = { packages ->
                viewModel.setPinned(slot, packages.firstOrNull())
                pinningSlot = null
            },
        )
    }
}

@Composable
private fun ConsoleRail(
    pinned: List<AppEntry>,
    pinnedSlots: Int,
    categories: List<CategoryWithApps>,
    selected: ConsolePane,
    iconProvider: suspend (AppEntry) -> ImageBitmap?,
    onSelect: (ConsolePane) -> Unit,
    onLaunchPinned: (AppEntry) -> Unit,
    onEditPin: (Int) -> Unit,
    onAddCategory: () -> Unit,
) {
    Column(
        Modifier
            .width(RAIL_WIDTH)
            .fillMaxHeight()
            .background(ZColors.SurfaceLow)
            .padding(WindowInsets.safeDrawing.asPaddingValues())
            .verticalScroll(rememberScrollState())
            .padding(vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("よく使う", style = ZType.Sub.copy(fontSize = 9.sp), color = ZColors.TextDim)
        Spacer(Modifier.height(8.dp))
        repeat(pinnedSlots) { slot ->
            PinSlot(
                entry = pinned.getOrNull(slot),
                iconProvider = iconProvider,
                onLaunch = onLaunchPinned,
                onEdit = { onEditPin(slot) },
            )
            Spacer(Modifier.height(8.dp))
        }

        Box(
            Modifier
                .padding(vertical = 8.dp)
                .width(36.dp)
                .height(1.dp)
                .background(ZColors.Outline)
        )

        RailItem(
            label = "概要",
            selected = selected is ConsolePane.Overview,
            indicator = { RailSquare(ZColors.AccentAlt) },
            onClick = { onSelect(ConsolePane.Overview) },
        )

        categories.forEach { category ->
            val color = ZColors.CategoryColors[category.category.colorIndex % ZColors.CategoryColors.size]
            RailItem(
                label = category.category.name,
                selected = (selected as? ConsolePane.Category)?.id == category.id,
                indicator = { Box(Modifier.size(14.dp).clip(CircleShape).background(color)) },
                onClick = { onSelect(ConsolePane.Category(category.id)) },
            )
        }

        RailItem(
            label = "追加",
            selected = false,
            indicator = { Text("＋", style = ZType.Title.copy(fontSize = 18.sp), color = ZColors.TextSecondary) },
            onClick = onAddCategory,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PinSlot(
    entry: AppEntry?,
    iconProvider: suspend (AppEntry) -> ImageBitmap?,
    onLaunch: (AppEntry) -> Unit,
    onEdit: () -> Unit,
) {
    if (entry == null) {
        Box(
            Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(13.dp))
                .background(ZColors.Accent.copy(alpha = 0.06f))
                .border(1.dp, ZColors.Accent.copy(alpha = 0.35f), RoundedCornerShape(13.dp))
                .clickable(onClick = onEdit),
            contentAlignment = Alignment.Center,
        ) {
            Text("＋", style = ZType.Body, color = ZColors.AccentSoft)
        }
        return
    }
    val icon by rememberAppIcon(entry, iconProvider)
    val haptics = LocalHapticFeedback.current
    // 差し替えは長押し。16dp のボタンを重ねるとタップ領域が小さすぎて押し分けられない
    Box(
        Modifier
            .clip(RoundedCornerShape(13.dp))
            .combinedClickable(
                onClick = { onLaunch(entry) },
                onLongClick = {
                    haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                    onEdit()
                },
            ),
        contentAlignment = Alignment.Center,
    ) {
        AppIconTile(icon = icon, size = 48.dp, background = ZColors.SurfaceHigh, border = ZColors.OutlineStrong)
    }
}

@Composable
private fun RailItem(
    label: String,
    selected: Boolean,
    indicator: @Composable () -> Unit,
    onClick: () -> Unit,
) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp, vertical = 3.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(if (selected) ZColors.SurfaceHigh else Color.Transparent)
            .clickable(onClick = onClick)
            .padding(vertical = 10.dp),
    ) {
        if (selected) {
            Box(
                Modifier
                    .align(Alignment.CenterStart)
                    .width(3.dp)
                    .height(26.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(ZColors.Accent)
            )
        }
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            indicator()
            Spacer(Modifier.height(6.dp))
            Text(
                text = label,
                style = ZType.Sub.copy(fontSize = 10.sp),
                color = if (selected) ZColors.TextPrimary else ZColors.TextSecondary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp),
            )
        }
    }
}

@Composable
private fun RailSquare(color: Color) {
    Box(
        Modifier
            .size(14.dp)
            .clip(RoundedCornerShape(4.dp))
            .border(2.dp, color, RoundedCornerShape(4.dp))
    )
}

@Composable
private fun ConsoleTopBar(
    title: String,
    subtitle: String,
    live: Boolean,
    isEditing: Boolean,
    showEdit: Boolean,
    onBack: () -> Unit,
    onToggleEdit: () -> Unit,
) {
    Row(
        Modifier.fillMaxWidth().padding(start = 12.dp, end = 16.dp, top = 4.dp, bottom = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Box(
            Modifier.size(32.dp).clip(RoundedCornerShape(8.dp)).clickable(onClick = onBack),
            contentAlignment = Alignment.Center,
        ) {
            Text("‹", style = ZType.Title.copy(fontSize = 22.sp), color = ZColors.TextSecondary)
        }
        Column(Modifier.weight(1f)) {
            Text(title, style = ZType.Title, color = ZColors.TextPrimary, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                if (live) LivePulse()
                Text(subtitle, style = ZType.Sub, color = ZColors.TextSecondary)
            }
        }
        if (showEdit) {
            Box(
                Modifier
                    .clip(RoundedCornerShape(999.dp))
                    .background(if (isEditing) ZColors.Accent.copy(alpha = 0.12f) else ZColors.Surface)
                    .border(
                        1.dp,
                        if (isEditing) ZColors.Accent.copy(alpha = 0.45f) else ZColors.Outline,
                        RoundedCornerShape(999.dp),
                    )
                    .clickable(onClick = onToggleEdit)
                    .padding(horizontal = 14.dp, vertical = 7.dp),
            ) {
                Text(
                    text = if (isEditing) "完了" else "編集",
                    style = ZType.Body,
                    color = if (isEditing) ZColors.AccentSoft else ZColors.TextSecondary,
                )
            }
        }
    }
}

/** 値が生きていることを示す明滅。静的な画面に見せないための最小限の動き */
@Composable
private fun LivePulse() {
    val transition = rememberInfiniteTransition(label = "live")
    val alpha by transition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(1100), RepeatMode.Reverse),
        label = "pulse",
    )
    Box(
        Modifier
            .size(6.dp)
            .alpha(alpha)
            .clip(CircleShape)
            .background(ZColors.AccentAlt)
    )
}

private fun formatClock(millis: Long): String =
    if (millis <= 0) "—" else SimpleDateFormat("H:mm:ss", Locale.getDefault()).format(Date(millis))
