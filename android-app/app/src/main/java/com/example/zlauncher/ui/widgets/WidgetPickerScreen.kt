package com.example.zlauncher.ui.widgets

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.zlauncher.core.designsystem.ZColors
import com.example.zlauncher.core.designsystem.ZType

private data class PendingWidget(val appWidgetId: Int, val item: WidgetPickerViewModel.ProviderItem)

/**
 * ウィジェットの追加フロー。
 *
 * 1. ID を払い出す
 * 2. `bindAppWidgetIdIfAllowed` を試す（既定ホームでなければ大抵 false）
 * 3. false なら ACTION_APPWIDGET_BIND でユーザーの同意を取る
 * 4. 設定画面を持つウィジェットなら ACTION_APPWIDGET_CONFIGURE を起動する
 * 5. 保存する
 *
 * どこで中断されても払い出した ID を返すのが肝。放置すると ID がリークする。
 */
@Composable
fun WidgetPickerScreen(
    onDone: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: WidgetPickerViewModel = hiltViewModel(),
) {
    val providers by viewModel.providers.collectAsStateWithLifecycle()
    val loading by viewModel.loading.collectAsStateWithLifecycle()
    var pending by remember { mutableStateOf<PendingWidget?>(null) }

    fun finish(target: PendingWidget) {
        viewModel.confirm(target.appWidgetId, target.item.minHeightDp)
        pending = null
        onDone()
    }

    val configureLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val target = pending
        if (target == null) return@rememberLauncherForActivityResult
        if (result.resultCode == Activity.RESULT_OK) {
            finish(target)
        } else {
            viewModel.cancel(target.appWidgetId)
            pending = null
        }
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
        val target = pending
        if (target == null) return@rememberLauncherForActivityResult
        if (result.resultCode == Activity.RESULT_OK) {
            configureOrFinish(target)
        } else {
            viewModel.cancel(target.appWidgetId)
            pending = null
        }
    }

    fun select(item: WidgetPickerViewModel.ProviderItem) {
        val appWidgetId = viewModel.allocateAppWidgetId()
        val target = PendingWidget(appWidgetId, item)
        pending = target
        if (viewModel.bindIfAllowed(appWidgetId, item.provider)) {
            configureOrFinish(target)
        } else {
            val intent = Intent(AppWidgetManager.ACTION_APPWIDGET_BIND).apply {
                putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
                putExtra(AppWidgetManager.EXTRA_APPWIDGET_PROVIDER, item.provider)
            }
            runCatching { bindLauncher.launch(intent) }.onFailure {
                viewModel.cancel(appWidgetId)
                pending = null
            }
        }
    }

    Column(
        modifier
            .fillMaxSize()
            .background(ZColors.Background)
            .padding(WindowInsets.safeDrawing.asPaddingValues()),
    ) {
        Row(
            Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp, bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Box(
                Modifier.size(32.dp).clip(RoundedCornerShape(8.dp)).clickable(onClick = onDone),
                contentAlignment = Alignment.Center,
            ) {
                Text("‹", style = ZType.Title.copy(fontSize = 22.sp), color = ZColors.TextSecondary)
            }
            Column(Modifier.weight(1f)) {
                Text("ウィジェットを追加", style = ZType.Title, color = ZColors.TextPrimary)
                Text(
                    text = if (loading) "読み込み中" else "${providers.size} 件",
                    style = ZType.Sub,
                    color = ZColors.TextSecondary,
                )
            }
        }

        LazyColumn(
            Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(providers, key = { it.provider.flattenToShortString() }) { item ->
                ProviderRow(item = item, onClick = { select(item) })
            }
        }
    }
}

@Composable
private fun ProviderRow(item: WidgetPickerViewModel.ProviderItem, onClick: () -> Unit) {
    val shape = RoundedCornerShape(12.dp)
    Row(
        Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(ZColors.Surface)
            .border(1.dp, ZColors.Outline, shape)
            .clickable(onClick = onClick)
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
            Text(item.label, style = ZType.Body, color = ZColors.TextPrimary)
            Text(item.sizeLabel, style = ZType.Sub, color = ZColors.TextSecondary)
        }
        if (item.configure != null) {
            Text("設定あり", style = ZType.Sub, color = ZColors.TextDim)
        }
    }
}
