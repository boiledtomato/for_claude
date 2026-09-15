package com.example.zlauncher.ui.widgets.totp

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.zlauncher.core.designsystem.ZColors
import com.example.zlauncher.core.designsystem.ZLauncherTheme
import com.example.zlauncher.core.designsystem.ZType
import com.example.zlauncher.core.ui.springyClick
import com.example.zlauncher.data.otp.OtpVault
import com.example.zlauncher.data.otp.OtpWidgetRenderer
import com.example.zlauncher.data.otp.OtpWidgetStore
import com.example.zlauncher.data.prefs.LauncherPreferencesRepository
import com.example.zlauncher.domain.model.ThemeMode
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * 置いたウィジェットに登録を 1 件割り当てる画面。
 *
 * **[com.example.zlauncher.data.otp.OtpRepository] を使わない。** あちらはコンソールの
 * 認証コードの面と同じ入れ物なので、ここで読み書きすると裏で開いている面の状態まで動く。
 * ここでは保管庫を直接読み、鍵を持たない表示用の形（[PickItem]）に落としてすぐ捨てる。
 */
@AndroidEntryPoint
class TotpWidgetConfigActivity : ComponentActivity() {

    @Inject
    lateinit var vault: OtpVault

    @Inject
    lateinit var store: OtpWidgetStore

    @Inject
    lateinit var renderer: OtpWidgetRenderer

    @Inject
    lateinit var preferences: LauncherPreferencesRepository

    /** 表示に要るものだけ。共有鍵はここへ持ってこない */
    data class PickItem(
        val id: String,
        val issuer: String,
        val account: String,
        val digits: Int,
        val periodSeconds: Int,
    ) {
        val label: String get() = if (issuer.isBlank()) account else issuer

        fun matches(query: String): Boolean {
            val needle = query.trim()
            if (needle.isEmpty()) return true
            return issuer.contains(needle, true) || account.contains(needle, true)
        }
    }

    private enum class Load { LOADING, READY, LOCKED, UNREADABLE }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val widgetId = intent?.getIntExtra(
            AppWidgetManager.EXTRA_APPWIDGET_ID,
            AppWidgetManager.INVALID_APPWIDGET_ID,
        ) ?: AppWidgetManager.INVALID_APPWIDGET_ID

        // 途中でやめられたときは「取り消し」で返す。これを先に置かないと、戻るで閉じた
        // ウィジェットがホーム画面に空のまま残る
        setResult(Activity.RESULT_CANCELED, Intent().putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId))
        if (widgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            // 他アプリから直に開かれた場合もここで終わる（一覧を見せない）
            finish()
            return
        }

        // 登録名の一覧が出る画面なので、認証コードの面と同じく撮影と録画を止める
        window.addFlags(WindowManager.LayoutParams.FLAG_SECURE)

        val themeModeFlow = preferences.state.map { it.themeMode }
        setContent {
            val themeMode by themeModeFlow.collectAsStateWithLifecycle(initialValue = ThemeMode.SYSTEM)
            var items by remember { mutableStateOf<List<PickItem>>(emptyList()) }
            var state by remember { mutableStateOf(Load.LOADING) }
            var query by remember { mutableStateOf("") }

            LaunchedEffect(Unit) {
                when (val result = withContext(Dispatchers.IO) { vault.load() }) {
                    is OtpVault.Result.Ready -> {
                        items = result.entries.map {
                            PickItem(it.id, it.issuer, it.account, it.digits, it.periodSeconds)
                        }
                        state = Load.READY
                    }

                    OtpVault.Result.Locked -> state = Load.LOCKED
                    OtpVault.Result.Unreadable -> state = Load.UNREADABLE
                }
            }

            ZLauncherTheme(mode = themeMode) {
                Screen(
                    state = state,
                    items = items,
                    query = query,
                    onQueryChange = { query = it },
                    onPick = { pick(widgetId, it) },
                )
            }
        }
    }

    private fun pick(widgetId: Int, item: PickItem) {
        store.bind(
            widgetId,
            OtpWidgetStore.Binding(
                entryId = item.id,
                issuer = item.issuer,
                account = item.account,
                digits = item.digits,
                periodSeconds = item.periodSeconds,
            ),
        )
        lifecycleScope.launch {
            withContext(Dispatchers.IO) { renderer.render(widgetId) }
            setResult(
                Activity.RESULT_OK,
                Intent().putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId),
            )
            finish()
        }
    }

    @Composable
    private fun Screen(
        state: Load,
        items: List<PickItem>,
        query: String,
        onQueryChange: (String) -> Unit,
        onPick: (PickItem) -> Unit,
    ) {
        val visible = remember(items, query) { items.filter { it.matches(query) } }

        Column(
            Modifier
                .fillMaxSize()
                .background(ZColors.Background)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text("Pick an account", style = ZType.Title, color = ZColors.TextPrimary)
            Text(
                "The widget shows this one account. Add another widget for each account you want on the home screen.",
                style = ZType.Body,
                color = ZColors.TextSecondary,
            )

            when (state) {
                Load.LOADING -> Notice("Reading the vault…")
                Load.LOCKED -> Notice("The vault is sealed while the device is locked. Unlock the phone and try again.")
                Load.UNREADABLE -> Notice("The vault could not be read. Open ZLauncher → Console → Auth to repair it.")
                Load.READY -> if (items.isEmpty()) {
                    Notice("No codes yet. Add them in Console → Auth, then place this widget.")
                } else {
                    if (items.size > SEARCH_THRESHOLD) {
                        SearchField(query, onQueryChange)
                    }
                    LazyColumn(
                        Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(bottom = 24.dp),
                    ) {
                        items(visible, key = { it.id }) { item ->
                            PickRow(item) { onPick(item) }
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun PickRow(item: PickItem, onClick: () -> Unit) {
        Column(
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(ZColors.Surface)
                .springyClick(onClick = onClick)
                .padding(horizontal = 14.dp, vertical = 12.dp),
        ) {
            Text(
                item.label,
                style = ZType.Body,
                color = ZColors.TextPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            if (item.issuer.isNotBlank() && item.account.isNotBlank()) {
                Text(
                    item.account,
                    style = ZType.Sub,
                    color = ZColors.TextSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Text(
                "${item.digits} digits · ${item.periodSeconds}s",
                style = ZType.Sub,
                color = ZColors.TextDim,
            )
        }
    }

    @Composable
    private fun Notice(message: String) {
        Box(
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(ZColors.Surface)
                .padding(16.dp),
            contentAlignment = Alignment.CenterStart,
        ) {
            Text(message, style = ZType.Body, color = ZColors.TextSecondary)
        }
    }

    @Composable
    private fun SearchField(query: String, onQueryChange: (String) -> Unit) {
        Box(
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(ZColors.SurfaceHigh)
                .padding(horizontal = 12.dp, vertical = 10.dp),
        ) {
            if (query.isEmpty()) {
                Text("Search", style = ZType.Body, color = ZColors.TextDim)
            }
            BasicTextField(
                value = query,
                onValueChange = onQueryChange,
                singleLine = true,
                textStyle = ZType.Body.copy(color = ZColors.TextPrimary),
                cursorBrush = SolidColor(ZColors.Accent),
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }

    private companion object {
        /** これより少なければ全部見えるので、検索欄は出さない */
        const val SEARCH_THRESHOLD = 6
    }
}
