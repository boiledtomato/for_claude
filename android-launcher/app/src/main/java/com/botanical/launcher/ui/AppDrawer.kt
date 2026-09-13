package com.botanical.launcher.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
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
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.botanical.launcher.data.AppEntry
import com.botanical.launcher.garden.Palette

sealed interface DrawerMode {
    /** ふつうにアプリを起動する */
    data object Browse : DrawerMode

    /**
     * 部位 [organId] に割り当てるアプリを選ぶ。複数選べる。
     * [label] は「蕾」「オオギキョウ」など表示名、[initial] は今入っているもの。
     */
    data class Assign(
        val organId: String,
        val label: String,
        val initial: List<String> = emptyList(),
    ) : DrawerMode
}

/**
 * 蕾が開いた先のアプリ一覧。押し花の標本帳に見えるよう、
 * アイコンは象牙色のメダイヨンに収め、書体はセリフで通す。
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppDrawer(
    apps: List<AppEntry>,
    mode: DrawerMode,
    onLaunch: (AppEntry) -> Unit,
    onConfirmAssign: (List<String>) -> Unit,
    onAppInfo: (AppEntry) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var query by remember { mutableStateOf("") }
    val assign = mode as? DrawerMode.Assign
    // 割り当て中の選択。順番どおりに束ねたいので List で持つ。
    val chosen = remember(assign?.organId) {
        mutableStateListOf<String>().apply { assign?.initial?.let { addAll(it) } }
    }
    val visible = remember(apps, query) {
        if (query.isBlank()) apps
        else apps.filter { it.label.contains(query.trim(), ignoreCase = true) }
    }

    Box(
        modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(listOf(Palette.Paper, Palette.PaperDeep, Palette.PaperShade)),
            ),
    ) {
        Column(Modifier.fillMaxSize().systemBarsPadding()) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 22.dp, end = 12.dp, top = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(Modifier.weight(1f)) {
                    Text(
                        text = when (mode) {
                            DrawerMode.Browse -> "Herbarium"
                            is DrawerMode.Assign -> "Assignatio"
                        },
                        style = TextStyle(
                            fontFamily = FontFamily.Serif,
                            fontSize = 24.sp,
                            fontStyle = FontStyle.Italic,
                            color = Palette.Ink,
                        ),
                    )
                    Text(
                        text = when (mode) {
                            DrawerMode.Browse -> "${apps.size} 点の標本"
                            is DrawerMode.Assign ->
                                "${mode.label.ifBlank { "この部位" }} に割り当てる" +
                                    if (chosen.isEmpty()) "（複数選べます）" else "（${chosen.size} 件）"
                        },
                        style = TextStyle(
                            fontFamily = FontFamily.Serif,
                            fontSize = 12.sp,
                            color = Palette.InkSoft,
                        ),
                    )
                }
                Text(
                    text = "✕",
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .combinedClickable(onClick = onDismiss)
                        .padding(horizontal = 14.dp, vertical = 6.dp),
                    style = TextStyle(
                        fontFamily = FontFamily.Serif,
                        fontSize = 22.sp,
                        color = Palette.Ink,
                    ),
                )
            }

            if (assign != null) {
                Row(
                    modifier = Modifier.padding(start = 18.dp, end = 18.dp, top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = if (chosen.isEmpty()) "選ばずに決定すると解除されます" else "決定する",
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .combinedClickable { onConfirmAssign(chosen.toList()) }
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        style = TextStyle(
                            fontFamily = FontFamily.Serif,
                            fontSize = 14.sp,
                            color = if (chosen.isEmpty()) Palette.Crimson else Palette.Green1,
                        ),
                    )
                    Spacer(Modifier.weight(1f))
                    if (chosen.isNotEmpty()) {
                        Text(
                            text = "選択を空に",
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .combinedClickable { chosen.clear() }
                                .padding(horizontal = 8.dp, vertical = 4.dp),
                            style = TextStyle(
                                fontFamily = FontFamily.Serif,
                                fontSize = 13.sp,
                                color = Palette.InkSoft,
                            ),
                        )
                    }
                }
            }

            SearchLine(
                query = query,
                onQueryChange = { query = it },
                modifier = Modifier.padding(start = 22.dp, end = 22.dp, top = 14.dp),
            )

            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 86.dp),
                contentPadding = PaddingValues(start = 12.dp, end = 12.dp, top = 16.dp, bottom = 28.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.fillMaxSize(),
            ) {
                items(visible, key = { it.key }) { app ->
                    AppMedallion(
                        app = app,
                        order = if (assign == null) 0 else chosen.indexOf(app.key) + 1,
                        onClick = {
                            if (assign == null) {
                                onLaunch(app)
                            } else if (!chosen.remove(app.key)) {
                                chosen.add(app.key)
                            }
                        },
                        onLongClick = { onAppInfo(app) },
                    )
                }
            }
        }
    }
}

@Composable
private fun SearchLine(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    BasicTextField(
        value = query,
        onValueChange = onQueryChange,
        singleLine = true,
        textStyle = TextStyle(
            fontFamily = FontFamily.Serif,
            fontSize = 16.sp,
            color = Palette.Ink,
        ),
        cursorBrush = SolidColor(Palette.Ink),
        modifier = modifier
            .fillMaxWidth()
            .drawBehind {
                drawLine(
                    color = Palette.Ink.copy(alpha = 0.45f),
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 1.2f,
                )
            }
            .padding(bottom = 6.dp),
        decorationBox = { inner ->
            Box {
                if (query.isEmpty()) {
                    Text(
                        text = "さがす",
                        style = TextStyle(
                            fontFamily = FontFamily.Serif,
                            fontSize = 16.sp,
                            fontStyle = FontStyle.Italic,
                            color = Palette.InkSoft.copy(alpha = 0.7f),
                        ),
                    )
                }
                inner()
            }
        },
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun AppMedallion(
    app: AppEntry,
    order: Int,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .combinedClickable(onClick = onClick, onLongClick = onLongClick)
            .padding(vertical = 10.dp, horizontal = 4.dp),
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.size(62.dp)) {
            Box(
                Modifier.fillMaxSize().drawBehind {
                    val r = size.minDimension / 2f
                    val c = Offset(size.width / 2f, size.height / 2f)
                    drawCircle(Palette.Cream, radius = r, center = c)
                    drawCircle(
                        color = Palette.Ink.copy(alpha = 0.55f),
                        radius = r,
                        center = c,
                        style = Stroke(width = 1.6f),
                    )
                    drawCircle(
                        color = Palette.Ink.copy(alpha = 0.22f),
                        radius = r * 0.90f,
                        center = c,
                        style = Stroke(width = 1f),
                    )
                    if (order > 0) {
                        drawCircle(Palette.Green1.copy(alpha = 0.16f), radius = r, center = c)
                        drawCircle(
                            color = Palette.Green1,
                            radius = r,
                            center = c,
                            style = Stroke(width = 3f),
                        )
                    }
                },
            )
            Image(
                bitmap = app.icon,
                contentDescription = app.label,
                modifier = Modifier.size(40.dp),
            )
        }
        Spacer(Modifier.height(6.dp))
        Text(
            text = if (order > 0) "$order. ${app.label}" else app.label,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontFamily = FontFamily.Serif,
                fontSize = 11.sp,
                color = Palette.Ink,
            ),
        )
    }
}
