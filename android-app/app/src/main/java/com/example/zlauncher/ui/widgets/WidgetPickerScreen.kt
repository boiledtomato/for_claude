package com.example.zlauncher.ui.widgets

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.zlauncher.core.designsystem.ZColors
import com.example.zlauncher.core.designsystem.ZType
import com.example.zlauncher.core.ui.springyClick
import com.example.zlauncher.ui.apps.component.AppSearchBar

private data class PendingWidget(val appWidgetId: Int, val item: WidgetPickerViewModel.ProviderItem)

/**
 * ウィジェットの追加フロー。
 *
 * 1 件ごとに:
 * 1. ID を払い出す
 * 2. `bindAppWidgetIdIfAllowed` を試す（既定ホームなら true。そうでなければ大抵 false）
 * 3. false なら ACTION_APPWIDGET_BIND でユーザーの同意を取る
 * 4. 設定画面を持つウィジェットなら ACTION_APPWIDGET_CONFIGURE を起動する
 * 5. 保存する
 *
 * どこで中断されても払い出した ID を返すのが肝。放置すると ID がリークする。
 *
 * **複数選択したときは、この一連を 1 件ずつ順番に流す。** 同意ダイアログも設定画面も
 * Activity なので同時には出せない。中断の扱いは 2 つで意味が違う:
 * - **同意を断られたら、残り全部をやめる。** 同じダイアログが人数分出るだけで、
 *   1 回断った人が次を許可することはまず無い
 * - **設定画面を閉じられたら、その 1 件だけ飛ばして次へ進む。** これは
 *   「このウィジェットは今はいい」であって、他の選択まで捨てる理由にはならない
 */
@Composable
fun WidgetPickerScreen(
    onDone: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: WidgetPickerViewModel = hiltViewModel(),
) {
    val providers by viewModel.providers.collectAsStateWithLifecycle()
    val loading by viewModel.loading.collectAsStateWithLifecycle()
    val query = viewModel.query
    val visible = remember(providers, query) { providers.filter { it.matches(query) } }

    var pending by remember { mutableStateOf<PendingWidget?>(null) }
    // 追加待ちの行列。指を離した後の処理はダイアログ越しに続くので、進み具合を画面に出す
    var queue by remember { mutableStateOf<List<WidgetPickerViewModel.ProviderItem>>(emptyList()) }
    var queueIndex by remember { mutableIntStateOf(0) }

    fun endQueue() {
        queue = emptyList()
        queueIndex = 0
        pending = null
        viewModel.clearSelection()
        onDone()
    }

    // 1 件処理し終えたら次へ。すべて終わったらホームへ戻す
    fun step() {
        val current = queue
        if (queueIndex >= current.size) {
            endQueue()
            return
        }
        val item = current[queueIndex]
        val appWidgetId = viewModel.allocateAppWidgetId()
        pending = PendingWidget(appWidgetId, item)
        // 実際の起動は下で定義したランチャーから行う（Composable のスコープの都合）
    }

    fun finish(target: PendingWidget) {
        viewModel.confirm(target.appWidgetId, target.item.minHeightDp)
        queueIndex += 1
        pending = null
        step()
    }

    /** その 1 件だけ捨てて次へ */
    fun skip(target: PendingWidget) {
        viewModel.cancel(target.appWidgetId)
        queueIndex += 1
        pending = null
        step()
    }

    /** 残り全部をやめる */
    fun abort(target: PendingWidget) {
        viewModel.cancel(target.appWidgetId)
        queueIndex = queue.size
        pending = null
        endQueue()
    }

    val configureLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val target = pending ?: return@rememberLauncherForActivityResult
        if (result.resultCode == Activity.RESULT_OK) finish(target) else skip(target)
    }

    fun configureOrFinish(target: PendingWidget) {
        val configure = target.item.configure
        if (configure == null) {
            finish(target)
            return
        }
        val intent = Intent(AppWidgetManager.ACTION_APPWIDGET_CONFIGURE).apply {
            component = configure
            putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, target.appWidgetId)
        }
        // 設定画面の起動は拒否されることがある。その場合は未設定のまま追加する
        runCatching { configureLauncher.launch(intent) }.onFailure { finish(target) }
    }

    val bindLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val target = pending ?: return@rememberLauncherForActivityResult
        // 同意を断られた = 残りも同じ結果になる。行列ごとやめる
        if (result.resultCode == Activity.RESULT_OK) configureOrFinish(target) else abort(target)
    }

    // 1 件ぶんのバインドを進める。
    // **鍵に queueIndex を入れる。** 直前の 1 件を取り消すと ID が解放され、次に払い出す
    // ID が同じ値になり得る。appWidgetId だけを鍵にすると鍵が変わらず、行列がそこで止まる
    LaunchedEffect(pending?.appWidgetId, queueIndex) {
        val target = pending ?: return@LaunchedEffect
        if (viewModel.bindIfAllowed(target.appWidgetId, target.item.provider)) {
            configureOrFinish(target)
        } else {
            val intent = Intent(AppWidgetManager.ACTION_APPWIDGET_BIND).apply {
                putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, target.appWidgetId)
                putExtra(AppWidgetManager.EXTRA_APPWIDGET_PROVIDER, target.item.provider)
            }
            runCatching { bindLauncher.launch(intent) }.onFailure { abort(target) }
        }
    }

    fun startAdding() {
        if (viewModel.selected.isEmpty()) return
        queue = viewModel.selected.toList()
        queueIndex = 0
        step()
    }

    Column(
        modifier
            .fillMaxSize()
            .background(ZColors.Background)
            .padding(WindowInsets.safeDrawing.asPaddingValues()),
    ) {
        Row(
            Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp, bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Box(
                Modifier.size(32.dp).clip(RoundedCornerShape(8.dp)).springyClick(onClick = onDone),
                contentAlignment = Alignment.Center,
            ) {
                Text("‹", style = ZType.Title.copy(fontSize = 22.sp), color = ZColors.TextSecondary)
            }
            Column(Modifier.weight(1f)) {
                Text("Add widgets", style = ZType.Title, color = ZColors.TextPrimary)
                Text(
                    text = when {
                        loading -> "Loading"
                        query.isNotBlank() -> "${visible.size} of ${providers.size} match"
                        else -> "${providers.size} available"
                    },
                    style = ZType.Sub,
                    color = ZColors.TextSecondary,
                )
            }
        }

        Box(Modifier.padding(horizontal = 16.dp)) {
            AppSearchBar(
                query = query,
                onQueryChange = viewModel::updateQuery,
                onClear = { viewModel.updateQuery("") },
                placeholder = "Search widgets or apps",
            )
        }

        LazyColumn(
            Modifier.weight(1f),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 10.dp, bottom = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(visible, key = { it.key }) { item ->
                ProviderRow(
                    item = item,
                    selected = viewModel.isSelected(item),
                    onClick = { viewModel.toggle(item) },
                )
            }
            if (!loading && visible.isEmpty()) {
                item(key = "empty") {
                    Text(
                        text = "No widget matches “$query”",
                        style = ZType.Body,
                        color = ZColors.TextDim,
                        modifier = Modifier.padding(vertical = 20.dp),
                    )
                }
            }
        }

        // 選んだものをまとめて入れる帯。検索で絞っても選択は残るので、
        // 「A を検索して選ぶ → B を検索して選ぶ → まとめて追加」ができる
        AnimatedVisibility(
            visible = viewModel.selected.isNotEmpty() && queue.isEmpty(),
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            SelectionBar(
                count = viewModel.selected.size,
                onClear = viewModel::clearSelection,
                onAdd = { startAdding() },
            )
        }

        AnimatedVisibility(visible = queue.isNotEmpty(), enter = fadeIn(), exit = fadeOut()) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(ZColors.SurfaceLow)
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    "Adding ${(queueIndex + 1).coerceAtMost(queue.size)} of ${queue.size}…",
                    style = ZType.Body,
                    color = ZColors.TextPrimary,
                )
            }
        }
    }
}

@Composable
private fun SelectionBar(count: Int, onClear: () -> Unit, onAdd: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(ZColors.SurfaceLow)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Text(
            text = if (count == 1) "1 selected" else "$count selected",
            style = ZType.Body,
            color = ZColors.TextSecondary,
            modifier = Modifier.weight(1f),
        )
        Text(
            "Clear",
            style = ZType.Body.copy(fontSize = 12.5.sp),
            color = ZColors.TextSecondary,
            modifier = Modifier
                .clip(RoundedCornerShape(999.dp))
                .background(ZColors.Surface)
                .border(1.dp, ZColors.Outline, RoundedCornerShape(999.dp))
                .springyClick(onClick = onClear)
                .padding(horizontal = 14.dp, vertical = 9.dp),
        )
        Text(
            text = if (count == 1) "Add widget" else "Add $count widgets",
            style = ZType.Body.copy(fontSize = 12.5.sp),
            color = ZColors.AccentSoft,
            modifier = Modifier
                .clip(RoundedCornerShape(999.dp))
                .background(ZColors.Accent.copy(alpha = 0.14f))
                .border(1.dp, ZColors.Accent.copy(alpha = 0.5f), RoundedCornerShape(999.dp))
                .springyClick(onClick = onAdd)
                .padding(horizontal = 16.dp, vertical = 9.dp),
        )
    }
}

@Composable
private fun ProviderRow(
    item: WidgetPickerViewModel.ProviderItem,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val shape = RoundedCornerShape(12.dp)
    Row(
        Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(if (selected) ZColors.Accent.copy(alpha = 0.10f) else ZColors.Surface)
            .border(
                1.dp,
                if (selected) ZColors.Accent.copy(alpha = 0.45f) else ZColors.Outline,
                shape,
            )
            .springyClick(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 11.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Box(
            Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(ZColors.SurfaceHigh),
            contentAlignment = Alignment.Center,
        ) {
            item.icon?.let { Image(bitmap = it, contentDescription = null, modifier = Modifier.size(26.dp)) }
        }
        Column(Modifier.weight(1f)) {
            Text(
                item.label,
                style = ZType.Body,
                color = ZColors.TextPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = buildString {
                    append(item.appLabel)
                    append(" · ")
                    append(item.sizeLabel)
                    if (item.configure != null) append(" · Configurable")
                },
                style = ZType.Sub,
                color = ZColors.TextSecondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        // 選択の印。もう一度押すと外れる
        Box(
            Modifier
                .size(20.dp)
                .clip(CircleShape)
                .then(
                    if (selected) Modifier.background(ZColors.Accent)
                    else Modifier.border(1.dp, ZColors.OutlineStrong, CircleShape)
                ),
            contentAlignment = Alignment.Center,
        ) {
            if (selected) {
                Text("✓", style = ZType.Sub.copy(fontSize = 11.sp), color = ZColors.Background)
            }
        }
    }
}
