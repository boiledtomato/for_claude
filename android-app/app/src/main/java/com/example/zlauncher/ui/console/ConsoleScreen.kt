package com.example.zlauncher.ui.console

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.togetherWith
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.runtime.LaunchedEffect
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
import com.example.zlauncher.core.designsystem.ZMotion
import com.example.zlauncher.core.designsystem.ZType
import com.example.zlauncher.core.ui.springyClick
import com.example.zlauncher.data.apps.CategoryWithApps
import com.example.zlauncher.domain.model.AppEntry
import com.example.zlauncher.ui.insights.InsightsPane
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
    var showCatalogDiff by remember { mutableStateOf(false) }
    val pendingDiff by viewModel.pendingCatalogDiff.collectAsStateWithLifecycle()
    val emptyCategories by viewModel.emptyCategories.collectAsStateWithLifecycle()

    // カタログは初回だけ読む。ダイアログを開いた瞬間に空、という状態を作らない
    LaunchedEffect(Unit) { viewModel.loadCatalog() }
    var editingCategory by remember { mutableStateOf<CategoryWithApps?>(null) }
    var pickingAppsFor by remember { mutableStateOf<CategoryWithApps?>(null) }
    var pinningSlot by remember { mutableStateOf<Int?>(null) }

    // 作成直後のアプリ選択。カテゴリーが Flow に現れてから開く
    LaunchedEffect(viewModel.pendingAppPrompt, categories) {
        val id = viewModel.pendingAppPrompt ?: return@LaunchedEffect
        categories.firstOrNull { it.id == id }?.let { target ->
            pickingAppsFor = target
            viewModel.consumeAppPrompt()
        }
    }

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
            onAddCategory = {
                viewModel.loadCatalog()
                showCreateDialog = true
            },
        )

        Column(
            Modifier
                .fillMaxSize()
                .padding(WindowInsets.safeDrawing.asPaddingValues()),
        ) {
            ConsoleTopBar(
                title = when {
                    selected is ConsolePane.Insights -> "Web Insights"
                    selectedCategory != null -> selectedCategory.category.name
                    else -> "Overview"
                },
                subtitle = when {
                    selected is ConsolePane.Insights -> "Per-category traffic log"
                    selectedCategory != null -> "${selectedCategory.apps.size} apps"
                    else -> "Live · updated ${formatClock(snapshot.metrics.sampledAtMillis)}"
                },
                live = selected is ConsolePane.Overview && snapshot.loaded,
                isEditing = viewModel.isEditing,
                showEdit = selected is ConsolePane.Overview,
                onBack = onBack,
                onToggleEdit = { viewModel.setEditMode(!viewModel.isEditing) },
            )

            pendingDiff?.let { diff ->
                CatalogUpdateBanner(
                    diff = diff,
                    onReview = { showCatalogDiff = true },
                    modifier = Modifier.padding(start = 12.dp, end = 16.dp, bottom = 10.dp),
                )
            }

            // 空のまま置き去りにされたカテゴリーを拾う導線。作成直後の選択を閉じた場合もここに出る
            // 今その空カテゴリーを見ているなら、ペイン側に同じ案内が出ているので帯は出さない
            val onlyShowingIt = emptyCategories.size == 1 &&
                (selected as? ConsolePane.Category)?.id == emptyCategories.first().id
            if (emptyCategories.isNotEmpty() && pickingAppsFor == null && !onlyShowingIt) {
                EmptyCategoriesBanner(
                    categories = emptyCategories,
                    onFill = { viewModel.promptForApps(emptyCategories.first().id) },
                    modifier = Modifier.padding(start = 12.dp, end = 16.dp, bottom = 10.dp),
                )
            }

            // ペインの切り替えも滑らせる。瞬間的に差し替えると場所を見失う
            AnimatedContent(
                targetState = selected,
                transitionSpec = {
                    (fadeIn(tween(ZMotion.TRANSITION_MS)) + slideInVertically { it / 18 })
                        .togetherWith(fadeOut(tween(160)))
                },
                label = "pane",
            ) { target ->
                when (target) {
                    is ConsolePane.Insights -> InsightsPane(initialCategoryId = target.categoryId)

                    is ConsolePane.Category -> {
                        val pane = categories.firstOrNull { it.id == target.id }
                        if (pane == null) {
                            // 表示中のカテゴリーが消えた瞬間。次の再構成で Overview に戻る
                            Box(Modifier.fillMaxSize())
                        } else {
                            CategoryPane(
                                category = pane,
                                iconProvider = viewModel::icon,
                                onLaunch = { entry -> viewModel.launch(entry) },
                                onRemoveApp = { pkg -> viewModel.removeAppFromCategory(pane.id, pkg) },
                                onPickApps = { pickingAppsFor = pane },
                                onEditCategory = { editingCategory = pane },
                                onDeleteCategory = { viewModel.deleteCategory(pane.id) },
                                onOpenInsights = { viewModel.select(ConsolePane.Insights(pane.id)) },
                            )
                        }
                    }

                    ConsolePane.Overview -> OverviewPane(
                        viewModel = viewModel,
                        snapshot = snapshot,
                        onAddWidget = onAddWidget,
                    )
                }
            }
        }
    }

    if (showCreateDialog) {
        CategoryCatalogDialog(
            groups = viewModel.catalogGroups,
            existingKeys = categories.mapNotNull { it.category.catalogKey }.toSet(),
            existingNames = categories.map { it.category.name }.toSet(),
            defaultColorIndex = categories.size % ZColors.CategoryColors.size,
            revisionLabel = viewModel.catalogRevision.ifBlank { "bundled" },
            onDismiss = { showCreateDialog = false },
            onConfirm = { entries, customName, customColor ->
                if (entries.isNotEmpty()) viewModel.createFromCatalog(entries)
                if (customName.isNotBlank()) viewModel.createCategory(customName, customColor)
                showCreateDialog = false
            },
        )
    }

    if (showCatalogDiff) {
        pendingDiff?.let { diff ->
            CatalogUpdateDialog(
                diff = diff,
                affectedCategoryCount = viewModel.affectedByDiff(diff),
                onDismiss = { showCatalogDiff = false },
                onApply = {
                    viewModel.applyCatalogDiff()
                    showCatalogDiff = false
                },
                onIgnore = {
                    viewModel.ignoreCatalogDiff()
                    showCatalogDiff = false
                },
            )
        }
    }

    editingCategory?.let { target ->
        CategoryEditDialog(
            title = "Edit category",
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
            title = "Apps in “${target.category.name}”",
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
            title = "Pin app (slot ${slot + 1})",
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
        Text("Pinned", style = ZType.Sub.copy(fontSize = 9.sp), color = ZColors.TextDim)
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
            label = "Overview",
            selected = selected is ConsolePane.Overview,
            indicator = { RailSquare(ZColors.AccentAlt) },
            onClick = { onSelect(ConsolePane.Overview) },
        )

        RailItem(
            label = "Insights",
            selected = selected is ConsolePane.Insights,
            indicator = { RailBars(ZColors.AccentSoft) },
            onClick = { onSelect(ConsolePane.Insights()) },
        )

        categories.forEach { category ->
            val color = ZColors.CategoryColors[category.category.colorIndex % ZColors.CategoryColors.size]
            val empty = category.apps.isEmpty()
            RailItem(
                label = category.category.name,
                selected = (selected as? ConsolePane.Category)?.id == category.id,
                indicator = {
                    // 空は塗らずに輪郭だけ。中身の有無をレール上で見分けられるようにする
                    Box(
                        Modifier
                            .size(14.dp)
                            .clip(CircleShape)
                            .then(
                                if (empty) Modifier.border(2.dp, color.copy(alpha = 0.55f), CircleShape)
                                else Modifier.background(color)
                            )
                    )
                },
                onClick = { onSelect(ConsolePane.Category(category.id)) },
            )
        }

        RailItem(
            label = "Add",
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
                .springyClick(onClick = onEdit),
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
    val background by animateColorAsState(
        targetValue = if (selected) ZColors.SurfaceHigh else Color.Transparent,
        animationSpec = ZMotion.value(),
        label = "railBackground",
    )
    val indicatorHeight by animateDpAsState(
        targetValue = if (selected) 26.dp else 0.dp,
        animationSpec = ZMotion.touch(),
        label = "railIndicator",
    )
    Box(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp, vertical = 3.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(background)
            .springyClick(onClick = onClick)
            .padding(vertical = 10.dp),
    ) {
        Box(
            Modifier
                .align(Alignment.CenterStart)
                .width(3.dp)
                .height(indicatorHeight)
                .clip(RoundedCornerShape(2.dp))
                .background(ZColors.Accent)
        )
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

/** Insights の目印。棒グラフを 3 本立てただけの図形 */
@Composable
private fun RailBars(color: Color) {
    Row(
        Modifier.size(14.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        listOf(0.45f, 1f, 0.7f).forEach { fraction ->
            Box(
                Modifier
                    .width(3.dp)
                    .fillMaxHeight(fraction)
                    .clip(RoundedCornerShape(1.dp))
                    .background(color)
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
            Modifier.size(32.dp).clip(RoundedCornerShape(8.dp)).springyClick(onClick = onBack),
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
                    .springyClick(onClick = onToggleEdit)
                    .padding(horizontal = 14.dp, vertical = 7.dp),
            ) {
                Text(
                    text = if (isEditing) "Done" else "Layout",
                    style = ZType.Body,
                    color = if (isEditing) ZColors.AccentSoft else ZColors.TextSecondary,
                )
            }
        }
    }
}

/**
 * 中身の無いカテゴリーを知らせる帯。
 *
 * 作成直後には選択ダイアログを開いているが、そこで閉じられると空のまま残る。
 * レールに名前だけが並んで「作ったのに何も入っていない」状態に気付けないので、
 * 上部から辿れるようにしておく。
 */
@Composable
private fun EmptyCategoriesBanner(
    categories: List<CategoryWithApps>,
    onFill: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(ZColors.StatusAmber.copy(alpha = 0.10f))
            .border(1.dp, ZColors.StatusAmber.copy(alpha = 0.35f), RoundedCornerShape(10.dp))
            .springyClick(onClick = onFill)
            .padding(horizontal = 11.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(9.dp),
    ) {
        Box(Modifier.size(7.dp).clip(CircleShape).background(ZColors.StatusAmber))
        Column(Modifier.weight(1f)) {
            Text(
                text = if (categories.size == 1) {
                    "“${categories.first().category.name}” has no apps yet"
                } else {
                    "${categories.size} categories have no apps yet"
                },
                style = ZType.Body,
                color = ZColors.TextPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                "A category only shows up in Insights once it contains apps",
                style = ZType.Sub,
                color = ZColors.TextSecondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Text("Select apps", style = ZType.Sub.copy(fontSize = 11.sp), color = ZColors.AccentSoft)
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
