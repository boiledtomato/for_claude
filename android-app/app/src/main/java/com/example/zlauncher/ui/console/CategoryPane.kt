package com.example.zlauncher.ui.console

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zlauncher.core.designsystem.ZColors
import com.example.zlauncher.core.designsystem.ZMotion
import com.example.zlauncher.core.designsystem.ZType
import com.example.zlauncher.core.ui.springyClick
import com.example.zlauncher.core.ui.springyCombinedClick
import com.example.zlauncher.data.apps.CategoryWithApps
import com.example.zlauncher.domain.model.AppEntry
import com.example.zlauncher.ui.apps.component.AppIconTile
import com.example.zlauncher.ui.apps.component.rememberAppIcon

private const val CATEGORY_COLUMNS = 4

/**
 * カテゴリーに入れ子にしたアプリの一覧。
 *
 * **外すのは長押しから始める専用のモード。** 以前は長押しで出るメニューの 1 項目が
 * その場で外していたので、押し間違いがそのまま反映された。今は
 *
 * 1. 長押しで取り外しモードに入り、対象が揺れる（消せるものだと動きで分かる）
 * 2. アイコン左上の `−` を目印に、外すものを選ぶ（複数まとめて選べる）
 * 3. 画面下に出るボタンを押す
 * 4. Yes / No で確認する
 *
 * という順になっている。揺れと確認の 2 段があるので、誤操作がそのまま消去にならない。
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryPane(
    category: CategoryWithApps,
    iconProvider: suspend (AppEntry) -> ImageBitmap?,
    onLaunch: (AppEntry) -> Unit,
    onRemoveApps: (List<String>) -> Unit,
    onPickApps: () -> Unit,
    onEditCategory: () -> Unit,
    onDeleteCategory: () -> Unit,
    onOpenInsights: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val color = ZColors.CategoryColors[category.category.colorIndex % ZColors.CategoryColors.size]
    var menuOpen by remember { mutableStateOf(false) }
    var removing by remember(category.category.id) { mutableStateOf(false) }
    val selected = remember(category.category.id) { mutableStateListOf<String>() }
    var confirming by remember { mutableStateOf(false) }

    fun exitRemoval() {
        removing = false
        selected.clear()
    }

    // 取り外しモード中の戻るは、まずモードを抜ける
    BackHandler(enabled = removing) { exitRemoval() }

    Box(modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize()) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 16.dp, bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Box(Modifier.size(10.dp).clip(CircleShape).background(color))
                Text(
                    text = if (removing) {
                        if (selected.isEmpty()) "Select apps to remove" else "${selected.size} selected"
                    } else {
                        "${category.apps.size} apps"
                    },
                    style = ZType.Sub,
                    color = if (removing) ZColors.Danger else ZColors.TextSecondary,
                    modifier = Modifier.weight(1f),
                )
                if (removing) {
                    ActionChip("Done", accent = true, onClick = { exitRemoval() })
                } else {
                    ActionChip("Insights", accent = false, onClick = onOpenInsights)
                    ActionChip("Select apps", accent = true, onClick = onPickApps)
                    Box {
                        ActionChip("⋯", accent = false, onClick = { menuOpen = true })
                        DropdownMenu(expanded = menuOpen, onDismissRequest = { menuOpen = false }) {
                            DropdownMenuItem(
                                text = { Text("Rename & recolor", style = ZType.Body, color = ZColors.TextPrimary) },
                                onClick = { menuOpen = false; onEditCategory() },
                            )
                            DropdownMenuItem(
                                text = { Text("Remove apps", style = ZType.Body, color = ZColors.TextPrimary) },
                                onClick = { menuOpen = false; removing = true },
                            )
                            DropdownMenuItem(
                                text = { Text("Delete category", style = ZType.Body, color = ZColors.StatusRed) },
                                onClick = { menuOpen = false; onDeleteCategory() },
                            )
                        }
                    }
                }
            }

            if (category.apps.isEmpty()) {
                EmptyCategory(onPickApps)
                return@Column
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(CATEGORY_COLUMNS),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    start = 8.dp,
                    end = 12.dp,
                    // 下のボタンがアイコンに重ならないよう、出ているぶんだけ空ける
                    bottom = if (removing && selected.isNotEmpty()) 96.dp else 28.dp,
                ),
                horizontalArrangement = Arrangement.spacedBy(2.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                itemsIndexed(category.apps, key = { _, entry -> entry.key }) { index, entry ->
                    CategoryAppTile(
                        modifier = Modifier.animateItem(placementSpec = ZMotion.placement()),
                        entry = entry,
                        index = index,
                        iconProvider = iconProvider,
                        removing = removing,
                        selected = selected.contains(entry.packageName),
                        onLaunch = { onLaunch(entry) },
                        onEnterRemoval = {
                            removing = true
                            if (!selected.contains(entry.packageName)) selected += entry.packageName
                        },
                        onToggleSelect = {
                            if (selected.contains(entry.packageName)) {
                                selected -= entry.packageName
                            } else {
                                selected += entry.packageName
                            }
                        },
                    )
                }
                item(span = { GridItemSpan(CATEGORY_COLUMNS) }) {
                    Spacer(Modifier.height(6.dp))
                }
            }
        }

        // 画面最下部の実行ボタン。選んでいる間だけ出す
        AnimatedVisibility(
            visible = removing && selected.isNotEmpty(),
            modifier = Modifier.align(Alignment.BottomCenter),
            enter = slideInVertically { it } + fadeIn(),
            exit = slideOutVertically { it } + fadeOut(),
        ) {
            RemoveBar(count = selected.size, onClick = { confirming = true })
        }
    }

    if (confirming) {
        val count = selected.size
        ConfirmDialog(
            title = "Remove from category",
            message = if (count == 1) {
                "Remove 1 app from “${category.category.name}”? The app stays installed."
            } else {
                "Remove $count apps from “${category.category.name}”? The apps stay installed."
            },
            onConfirm = {
                onRemoveApps(selected.toList())
                confirming = false
                exitRemoval()
            },
            onDismiss = { confirming = false },
        )
    }
}

/**
 * 下端の実行ボタン。
 *
 * 押すと消えるものなので、ここだけ他と違う色を持たせる（暗い配色ではピンク地に白）。
 * 面の中の操作と同じ見た目にすると、並んだチップの 1 つとして押されてしまう。
 */
@Composable
private fun RemoveBar(count: Int, onClick: () -> Unit) {
    Box(Modifier.fillMaxWidth().padding(start = 12.dp, end = 16.dp, bottom = 18.dp)) {
        Box(
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(999.dp))
                .background(ZColors.Danger)
                .springyClick(onClick = onClick)
                .padding(vertical = 14.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = if (count == 1) "Remove from category" else "Remove $count from category",
                style = ZType.Body.copy(fontSize = 13.5.sp),
                color = ZColors.OnDanger,
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CategoryAppTile(
    modifier: Modifier = Modifier,
    entry: AppEntry,
    index: Int,
    iconProvider: suspend (AppEntry) -> ImageBitmap?,
    removing: Boolean,
    selected: Boolean,
    onLaunch: () -> Unit,
    onEnterRemoval: () -> Unit,
    onToggleSelect: () -> Unit,
) {
    val icon by rememberAppIcon(entry, iconProvider)

    // 揺れは 1 枚ずつ位相をずらす。揃って動くと画面全体が波打って読みにくい
    val transition = rememberInfiniteTransition(label = "jiggle")
    val angle by transition.animateFloat(
        initialValue = -ZMotion.JIGGLE_DEGREES,
        targetValue = ZMotion.JIGGLE_DEGREES,
        animationSpec = infiniteRepeatable(
            animation = tween(ZMotion.JIGGLE_MS),
            repeatMode = RepeatMode.Reverse,
            initialStartOffset = StartOffset((index % 4) * (ZMotion.JIGGLE_MS / 4)),
        ),
        label = "jiggleAngle",
    )

    // 取り外しモードでは角をさらに丸める。触れる対象が変わったことを形でも示す
    val shape = RoundedCornerShape(if (removing) 26.dp else 16.dp)

    Box(modifier) {
        Column(
            Modifier
                .fillMaxWidth()
                .graphicsLayer { rotationZ = if (removing) angle else 0f }
                .clip(shape)
                .background(
                    when {
                        selected -> ZColors.Danger.copy(alpha = 0.16f)
                        removing -> ZColors.Surface.copy(alpha = 0.6f)
                        else -> androidx.compose.ui.graphics.Color.Transparent
                    }
                )
                .then(
                    if (selected) Modifier.border(1.5.dp, ZColors.Danger, shape) else Modifier
                )
                .then(
                    if (removing) {
                        Modifier.springyClick(onClick = onToggleSelect)
                    } else {
                        Modifier.springyCombinedClick(onClick = onLaunch, onLongClick = onEnterRemoval)
                    }
                )
                .padding(vertical = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Box {
                AppIconTile(icon = icon, size = 56.dp)
                if (removing) {
                    MinusBadge(
                        selected = selected,
                        modifier = Modifier.align(Alignment.TopStart),
                    )
                }
            }
            Text(
                text = entry.label,
                style = ZType.AppLabel,
                color = if (selected) ZColors.TextPrimary else ZColors.TextSecondary,
                minLines = 2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 2.dp),
            )
        }
    }
}

/** アイコン左上の `−`。選んだものだけ塗る */
@Composable
private fun MinusBadge(selected: Boolean, modifier: Modifier = Modifier) {
    Box(
        modifier
            .size(20.dp)
            .clip(CircleShape)
            .background(if (selected) ZColors.Danger else ZColors.SurfaceHigh)
            .border(1.dp, if (selected) ZColors.Danger else ZColors.OutlineStrong, CircleShape),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            Modifier
                .size(width = 10.dp, height = 2.dp)
                .background(if (selected) ZColors.OnDanger else ZColors.TextSecondary),
        )
    }
}

/**
 * 中身が無いときの案内。
 *
 * 「空です」とだけ出しても次に何をすればいいか分からないので、
 * 埋めないと何が起きないのか（Insights に出てこない）まで書く。
 */
@Composable
private fun EmptyCategory(onPickApps: () -> Unit) {
    Column(
        Modifier.fillMaxSize().padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(40.dp))
        Text("No apps in this category yet", style = ZType.Body, color = ZColors.TextPrimary)
        Spacer(Modifier.height(6.dp))
        Text(
            text = "Pick the apps that belong here. Until then this category stays empty " +
                "and contributes nothing to Web Insights.",
            style = ZType.Sub,
            color = ZColors.TextSecondary,
            textAlign = TextAlign.Center,
        )
        Spacer(Modifier.height(16.dp))
        ActionChip("Select apps", accent = true, onClick = onPickApps)
    }
}

@Composable
private fun ActionChip(label: String, accent: Boolean, onClick: () -> Unit) {
    Box(
        Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(if (accent) ZColors.Accent.copy(alpha = 0.12f) else ZColors.Surface)
            .border(
                1.dp,
                if (accent) ZColors.Accent.copy(alpha = 0.45f) else ZColors.Outline,
                RoundedCornerShape(999.dp),
            )
            .springyClick(onClick = onClick)
            .padding(horizontal = 13.dp, vertical = 7.dp),
    ) {
        Text(
            label,
            style = ZType.Body.copy(fontSize = 12.sp),
            color = if (accent) ZColors.AccentSoft else ZColors.TextSecondary,
        )
    }
}
