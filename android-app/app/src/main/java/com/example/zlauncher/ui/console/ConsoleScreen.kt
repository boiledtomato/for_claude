package com.example.zlauncher.ui.console

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
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
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.zlauncher.core.designsystem.ZColors
import com.example.zlauncher.core.designsystem.ZMotion
import com.example.zlauncher.core.designsystem.ZType
import com.example.zlauncher.core.ui.springyClick
import com.example.zlauncher.data.apps.CategoryWithApps
import com.example.zlauncher.data.widgets.WidgetHostController
import com.example.zlauncher.domain.model.AppEntry
import com.example.zlauncher.ui.apps.component.AppIconTile
import com.example.zlauncher.ui.apps.component.rememberAppIcon
import com.example.zlauncher.ui.insights.InsightsPane
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlinx.coroutines.launch

private val RAIL_WIDTH = 84.dp

@Composable
fun ConsoleScreen(
    onOpenApps: () -> Unit,
    onAddWidget: () -> Unit,
    widgetHost: WidgetHostController,
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
    val pinnedExpanded by viewModel.pinnedExpanded.collectAsStateWithLifecycle()
    val categoriesExpanded by viewModel.categoriesExpanded.collectAsStateWithLifecycle()

    // カタログは初回だけ読む。ダイアログを開いた瞬間に空、という状態を作らない
    LaunchedEffect(Unit) { viewModel.loadCatalog() }
    var editingCategory by remember { mutableStateOf<CategoryWithApps?>(null) }
    var pickingAppsFor by remember { mutableStateOf<CategoryWithApps?>(null) }
    // 開いているアプリ選択が「作りたてのカテゴリー」のものか。閉じたときの意味が変わる
    var pickingIsNew by remember { mutableStateOf(false) }
    var pinningSlot by remember { mutableStateOf<Int?>(null) }

    // 作成直後のアプリ選択。カテゴリーが Flow に現れてから開く
    LaunchedEffect(viewModel.pendingAppPrompt, categories) {
        val id = viewModel.pendingAppPrompt ?: return@LaunchedEffect
        categories.firstOrNull { it.id == id }?.let { target ->
            pickingAppsFor = target
            pickingIsNew = viewModel.promptIsNewCategory
            viewModel.consumeAppPrompt()
        }
    }

    val selected = viewModel.pane
    val selectedCategory = (selected as? ConsolePane.Category)?.let { pane ->
        categories.firstOrNull { it.id == pane.id }
    }

    // この画面がホーム。戻る操作は飲み込む（ランチャーの Activity は終了しないのが正しい）。
    // Overview 以外を見ているときだけは、まず Overview へ戻す
    BackHandler(enabled = true) {
        if (selected !is ConsolePane.Overview) viewModel.select(ConsolePane.Overview)
    }

    Row(modifier.fillMaxSize().background(ZColors.Background)) {
        ConsoleRail(
            onOpenApps = onOpenApps,
            pinned = pinned,
            pinnedSlots = viewModel.pinnedSlots,
            pinnedExpanded = pinnedExpanded,
            onTogglePinned = viewModel::togglePinned,
            categories = categories,
            categoriesExpanded = categoriesExpanded,
            onToggleCategories = viewModel::toggleCategories,
            selected = selected,
            iconProvider = viewModel::icon,
            onSelect = viewModel::select,
            onLaunchPinned = { viewModel.launch(it) },
            onEditPin = { slot -> pinningSlot = slot },
            onAddCategory = {
                viewModel.loadCatalog()
                showCreateDialog = true
            },
            onMoveCategory = viewModel::moveCategory,
        )

        Column(
            Modifier
                .fillMaxSize()
                .padding(WindowInsets.safeDrawing.asPaddingValues()),
        ) {
            ConsoleTopBar(
                title = when {
                    selected is ConsolePane.Insights -> "Web Insights"
                    selected is ConsolePane.Widgets -> "Widgets"
                    selectedCategory != null -> selectedCategory.category.name
                    else -> "Overview"
                },
                subtitle = when {
                    selected is ConsolePane.Insights -> "Per-category traffic log"
                    selected is ConsolePane.Widgets -> "Placed on this screen"
                    selectedCategory != null -> "${selectedCategory.apps.size} apps"
                    else -> "Live · updated ${formatClock(snapshot.metrics.sampledAtMillis)}"
                },
                live = selected is ConsolePane.Overview && snapshot.loaded,
                isEditing = viewModel.isEditing,
                showEdit = selected is ConsolePane.Overview,
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

                    ConsolePane.Widgets -> WidgetsPane(
                        viewModel = viewModel,
                        widgetHost = widgetHost,
                        onAddWidget = onAddWidget,
                    )

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
                                onPickApps = { pickingAppsFor = pane; pickingIsNew = false },
                                onEditCategory = { editingCategory = pane },
                                onDeleteCategory = { viewModel.deleteCategory(pane.id) },
                                onOpenInsights = { viewModel.select(ConsolePane.Insights(pane.id)) },
                            )
                        }
                    }

                    ConsolePane.Overview -> OverviewPane(
                        viewModel = viewModel,
                        snapshot = snapshot,
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
            onConfirm = { picks, customName, customColor ->
                if (picks.isNotEmpty()) viewModel.createFromCatalog(picks)
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
        // 開いた時点の一覧に対して 1 度だけ推定する。毎フレーム引き直さない
        val suggestions = remember(target.id, allApps) { viewModel.suggestionsFor(target) }
        AppPickerDialog(
            title = if (pickingIsNew) "New category “${target.category.name}”" else "Apps in “${target.category.name}”",
            apps = allApps,
            initiallySelected = target.category.packages.toSet(),
            multiSelect = true,
            iconProvider = viewModel::icon,
            // 作りたてなら閉じる = 作成の取り止め。空のカテゴリーだけを残して終わらない
            dismissLabel = if (pickingIsNew) "Discard" else "Cancel",
            dismissNote = if (pickingIsNew) "Discard removes the category you just added" else null,
            onDismiss = {
                if (pickingIsNew) viewModel.discardNewCategory(target.id)
                pickingAppsFor = null
                pickingIsNew = false
            },
            onConfirm = { packages ->
                viewModel.setCategoryApps(target.id, packages)
                pickingAppsFor = null
                pickingIsNew = false
            },
            suggestions = suggestions,
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
    onOpenApps: () -> Unit,
    pinned: List<AppEntry>,
    pinnedSlots: Int,
    pinnedExpanded: Boolean,
    onTogglePinned: () -> Unit,
    categories: List<CategoryWithApps>,
    categoriesExpanded: Boolean,
    onToggleCategories: () -> Unit,
    selected: ConsolePane,
    iconProvider: suspend (AppEntry) -> ImageBitmap?,
    onSelect: (ConsolePane) -> Unit,
    onLaunchPinned: (AppEntry) -> Unit,
    onEditPin: (Int) -> Unit,
    onAddCategory: () -> Unit,
    onMoveCategory: (Int, Int) -> Unit,
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
        // ここがホーム。全アプリの一覧はドロワーとして別画面に出す。
        // ランチャーの本業なので、レールの一番上から 1 タップで届く場所に置く
        RailItem(
            label = "Apps",
            selected = false,
            indicator = { RailGrid(ZColors.Accent) },
            onClick = onOpenApps,
        )

        RailDivider()

        // 枠が 4 つあるとレールの上半分をピンが占める。畳んで場所を返せるようにする
        RailSectionHeader(
            label = "Pinned",
            collapsedLabel = "Pinned ${pinned.size}/$pinnedSlots",
            expanded = pinnedExpanded,
            onClick = onTogglePinned,
        )
        AnimatedVisibility(
            visible = pinnedExpanded,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically(),
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
            }
        }

        RailDivider()

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

        RailItem(
            label = "Widgets",
            selected = selected is ConsolePane.Widgets,
            indicator = { RailFrame(ZColors.AccentSoft) },
            onClick = { onSelect(ConsolePane.Widgets) },
        )

        // Overview / Insights は据え置きの機能、以下は自分で作った URL カテゴリー。
        // 同じ見た目で続けると境目が消えるので、ピン留めと同じ区切り線と見出しを挟む
        RailDivider()

        RailSectionHeader(
            // 84dp のレールに収まる長さで。自作の自由入力カテゴリーもここに並ぶので
            // "URL categories" ではなく総称にしてある
            label = "Categories",
            collapsedLabel = "Categories ${categories.size}",
            expanded = categoriesExpanded,
            onClick = onToggleCategories,
        )

        AnimatedVisibility(
            visible = categoriesExpanded,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically(),
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                CategoryRailItems(
                    categories = categories,
                    selected = selected,
                    onSelect = onSelect,
                    onMove = onMoveCategory,
                )

                RailItem(
                    label = "Add",
                    selected = false,
                    indicator = {
                        Text("＋", style = ZType.Title.copy(fontSize = 18.sp), color = ZColors.TextSecondary)
                    },
                    onClick = onAddCategory,
                )
            }
        }
    }
}

/**
 * カテゴリーの並び。長押しで持ち上げて上下に入れ替えられる。
 *
 * **並べ替えは指を離してから確定する。** 途中で確定させると行と要素の対応が変わり、
 * その行の pointerInput が作り直されて、動かしている最中に指が外れる。
 * 動いている間に見えているのは [RailReorder.shift] が計算する見た目のずれだけ。
 */
@Composable
private fun CategoryRailItems(
    categories: List<CategoryWithApps>,
    selected: ConsolePane,
    onSelect: (ConsolePane) -> Unit,
    onMove: (Int, Int) -> Unit,
) {
    val haptics = LocalHapticFeedback.current
    val scope = rememberCoroutineScope()
    // 行の高さは名前が 2 行に折り返すかで変わるので実測しておく。
    // 件数が変われば作り直す（合成中に書き換えると読み書きの順で挙動が変わる）
    val heights = remember(categories.size) {
        mutableStateListOf<Float>().apply { repeat(categories.size) { add(0f) } }
    }
    var dragStart by remember { mutableIntStateOf(-1) }
    var dragDy by remember { mutableFloatStateOf(0f) }
    // 指を離した後、残りのずれを吸収するあいだだけ立つ。即座に 0 にすると行が飛ぶ
    var settlingIndex by remember { mutableIntStateOf(-1) }
    val settleDy = remember { Animatable(0f) }

    val target = if (dragStart >= 0) RailReorder.drop(dragStart, dragDy, heights).index else -1

    categories.forEachIndexed { index, category ->
        val color = ZColors.CategoryColors[category.category.colorIndex % ZColors.CategoryColors.size]
        val empty = category.apps.isEmpty()
        val lifted = dragStart == index
        val shift = if (dragStart >= 0) RailReorder.shift(index, dragStart, target, heights) else 0f
        val shifted by animateFloatAsState(
            targetValue = shift,
            animationSpec = ZMotion.touch(),
            label = "railShift",
        )
        val offset = when {
            lifted -> dragDy
            index == settlingIndex -> settleDy.value
            else -> shifted
        }
        val lift by animateFloatAsState(
            targetValue = if (lifted) ZMotion.LIFT_SCALE else 1f,
            animationSpec = ZMotion.touch(),
            label = "railLift",
        )
        Box(
            Modifier
                .onSizeChanged { size ->
                    if (size.height > 0 && index < heights.size) heights[index] = size.height.toFloat()
                }
                .zIndex(if (lifted || offset != 0f) 1f else 0f)
                .graphicsLayer {
                    translationY = offset
                    scaleX = lift
                    scaleY = lift
                }
                .pointerInput(category.id, categories.size) {
                    if (categories.size < 2) return@pointerInput
                    detectDragGesturesAfterLongPress(
                        onDragStart = {
                            haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                            dragStart = index
                            dragDy = 0f
                        },
                        onDrag = { change, delta ->
                            change.consume()
                            if (dragStart >= 0) dragDy += delta.y
                        },
                        onDragEnd = {
                            val from = dragStart
                            if (from < 0) return@detectDragGesturesAfterLongPress
                            val drop = RailReorder.drop(from, dragDy, heights)
                            // 確定で行はこのぶん動く。残りだけ戻せば指を離した位置から続く
                            val residual = dragDy - drop.consumed
                            dragStart = -1
                            dragDy = 0f
                            if (drop.index != from) onMove(from, drop.index)
                            settlingIndex = drop.index
                            scope.launch {
                                settleDy.snapTo(residual)
                                settleDy.animateTo(0f, ZMotion.touch())
                                settlingIndex = -1
                            }
                        },
                        onDragCancel = {
                            dragStart = -1
                            dragDy = 0f
                        },
                    )
                },
        ) {
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
    }
}

@Composable
private fun RailDivider() {
    Box(
        Modifier
            .padding(vertical = 8.dp)
            .width(36.dp)
            .height(1.dp)
            .background(ZColors.Outline)
    )
}

/**
 * レールの見出し。タブとして押すと下の中身を開閉する。
 *
 * 畳んでいるときも件数は出す。畳んだ結果、そこに何かあること自体を忘れる、
 * という状態を作らないため。
 */
@Composable
private fun RailSectionHeader(
    label: String,
    collapsedLabel: String,
    expanded: Boolean,
    onClick: () -> Unit,
) {
    val rotation by animateFloatAsState(
        targetValue = if (expanded) 90f else 0f,
        animationSpec = ZMotion.touch(),
        label = "railChevron",
    )
    val background by animateColorAsState(
        targetValue = if (expanded) Color.Transparent else ZColors.Surface,
        animationSpec = ZMotion.value(),
        label = "railHeader",
    )
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(background)
            .springyClick(onClick = onClick)
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            "›",
            style = ZType.Sub.copy(fontSize = 11.sp),
            color = ZColors.TextDim,
            modifier = Modifier.rotate(rotation),
        )
        Spacer(Modifier.width(4.dp))
        Text(
            text = if (expanded) label else collapsedLabel,
            style = ZType.Sub.copy(fontSize = 9.sp),
            color = ZColors.TextDim,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
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

/** Apps の目印。4 マスのグリッド */
@Composable
private fun RailGrid(color: Color) {
    Column(
        Modifier.size(14.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        repeat(2) {
            Row(Modifier.weight(1f), horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                repeat(2) {
                    Box(
                        Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(1.5.dp))
                            .background(color)
                    )
                }
            }
        }
    }
}

/** Widgets の目印。中に横線の入った枠 */
@Composable
private fun RailFrame(color: Color) {
    Box(
        Modifier
            .size(14.dp)
            .clip(RoundedCornerShape(3.dp))
            .border(1.5.dp, color, RoundedCornerShape(3.dp)),
        contentAlignment = Alignment.Center,
    ) {
        Box(Modifier.width(8.dp).height(1.5.dp).background(color))
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
    onToggleEdit: () -> Unit,
) {
    Row(
        Modifier.fillMaxWidth().padding(start = 12.dp, end = 16.dp, top = 4.dp, bottom = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
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
