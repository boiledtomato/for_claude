package com.botanical.launcher.ui

import android.graphics.Bitmap
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.botanical.launcher.data.AppEntry
import com.botanical.launcher.flora.Palette

sealed interface DrawerMode {
    /** ふつうにアプリを起動する */
    data object Browse : DrawerMode

    /**
     * 部位 [organId] に割り当てるアプリを選ぶ。複数選べる。
     * [label] は「花」「葉」など表示名、[initial] は今入っているもの。
     * [sprite] はその部位そのものの絵。どこに登録しているのかを、
     * 名前ではなく絵で示すためにここまで持ってくる。
     */
    data class Assign(
        val organId: String,
        val label: String,
        val initial: List<String> = emptyList(),
        val sprite: Bitmap? = null,
    ) : DrawerMode
}

private val serif = FontFamily.Serif

private fun title(size: Int) = TextStyle(
    fontFamily = serif, fontSize = size.sp, fontStyle = FontStyle.Italic, color = Palette.Ink,
)

private fun body(size: Int, color: androidx.compose.ui.graphics.Color = Palette.InkSoft) =
    TextStyle(fontFamily = serif, fontSize = size.sp, color = color)

/**
 * 蕾が開いた先のアプリ一覧。押し花の標本帳に見えるよう、
 * アイコンは象牙色のメダイヨンに収め、書体はセリフで通す。
 *
 * 割り当て中は上に標本ラベルを出す。「どの部位に」「いま何が入っていて」
 * 「次はどこに足されるか」を一目で見せるためで、決定も画面下の固定バーに
 * 置いてある。文字リンクひとつでは、押せることにも結果にも気づけない。
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
    // 割り当て中の選択。押した順に束ねたいので List で持つ。
    val chosen = remember(assign?.organId) {
        mutableStateListOf<String>().apply { assign?.initial?.let { addAll(it) } }
    }
    val visible = remember(apps, query) {
        if (query.isBlank()) apps
        else apps.filter { it.label.contains(query.trim(), ignoreCase = true) }
    }
    val appsByKey = remember(apps) { apps.associateBy { it.key } }

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
                        text = if (assign == null) "Herbarium" else "Assignatio",
                        style = title(24),
                    )
                    Text(
                        text = if (assign == null) "${apps.size} 点の標本"
                        else "標本のひとつを、この部位に留める",
                        style = body(12),
                    )
                }
                Text(
                    text = "✕",
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .combinedClickable(onClick = onDismiss)
                        .padding(horizontal = 14.dp, vertical = 6.dp),
                    style = TextStyle(fontFamily = serif, fontSize = 22.sp, color = Palette.Ink),
                )
            }

            if (assign != null) {
                SpecimenLabel(
                    assign = assign,
                    chosen = chosen,
                    appsByKey = appsByKey,
                    onRemove = { chosen.remove(it) },
                    modifier = Modifier.padding(start = 18.dp, end = 18.dp, top = 12.dp),
                )
            }

            SearchLine(
                query = query,
                onQueryChange = { query = it },
                modifier = Modifier.padding(start = 22.dp, end = 22.dp, top = 14.dp),
            )

            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 86.dp),
                contentPadding = PaddingValues(
                    start = 12.dp, end = 12.dp, top = 16.dp,
                    bottom = if (assign == null) 28.dp else 96.dp,
                ),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.weight(1f),
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

        // 決定は画面下に固定する。一覧を下までたどってから探し回らずに済む。
        if (assign != null) {
            ConfirmBar(
                count = chosen.size,
                hadBinding = assign.initial.isNotEmpty(),
                onClear = { chosen.clear() },
                onConfirm = { onConfirmAssign(chosen.toList()) },
                modifier = Modifier.align(Alignment.BottomCenter),
            )
        }
    }
}

/**
 * 標本ラベル。押し花を貼った台紙に添える、あの手書きの札。
 * 左に部位そのものの絵、右に留めたアプリが押した順に並ぶ。
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SpecimenLabel(
    assign: DrawerMode.Assign,
    chosen: List<String>,
    appsByKey: Map<String, AppEntry>,
    onRemove: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .background(Palette.Cream)
            .ruledBorder()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // 部位の絵。名前より絵の方が「どこに登録しているか」がすぐ分かる。
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(66.dp).clip(RoundedCornerShape(3.dp)).drawBehind {
                drawRect(Palette.Paper)
                drawRect(Palette.Ink.copy(alpha = 0.30f), style = Stroke(width = 1.2f))
            },
        ) {
            val bmp = assign.sprite
            if (bmp != null) {
                Image(
                    bitmap = bmp.asImageBitmap(),
                    contentDescription = assign.label,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(56.dp),
                )
            } else {
                Text(assign.label, style = title(18))
            }
        }

        Spacer(Modifier.width(14.dp))

        Column(Modifier.weight(1f)) {
            Text(assign.label, style = title(17))
            Spacer(Modifier.height(2.dp))
            Text(
                text = when {
                    chosen.isEmpty() -> "まだ何も留めていません"
                    chosen.size == 1 -> "1 件 ・ 押すとすぐ咲いて開きます"
                    else -> "${chosen.size} 件 ・ 蔦が分かれて並びます"
                },
                style = body(11),
            )
            Spacer(Modifier.height(8.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                itemsIndexed(chosen, key = { _, k -> k }) { i, key ->
                    val app = appsByKey[key]
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(34.dp)
                            .clip(RoundedCornerShape(50))
                            .combinedClickable { onRemove(key) }
                            .drawBehind {
                                val r = size.minDimension / 2f
                                val c = Offset(size.width / 2f, size.height / 2f)
                                drawCircle(Palette.Paper, r, c)
                                drawCircle(Palette.Ink.copy(alpha = 0.5f), r, c,
                                    style = Stroke(width = 1.3f))
                            },
                    ) {
                        if (app != null) {
                            Image(app.icon, app.label, Modifier.size(21.dp))
                        } else {
                            Text("${i + 1}", style = body(11, Palette.Ink))
                        }
                    }
                }
                // 次がどこに足されるかを、破線の空き枠で見せる。
                item {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.size(34.dp).drawBehind {
                            val r = size.minDimension / 2f - 1f
                            drawCircle(
                                color = Palette.Ink.copy(alpha = 0.35f),
                                radius = r,
                                center = Offset(size.width / 2f, size.height / 2f),
                                style = Stroke(
                                    width = 1.2f,
                                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(4f, 4f)),
                                ),
                            )
                        },
                    ) {
                        Text("＋", style = body(13, Palette.InkSoft))
                    }
                }
            }
        }
    }
}

/** 決定バー。画面の下に固定して、押せることが形で分かるようにする。 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ConfirmBar(
    count: Int,
    hadBinding: Boolean,
    onClear: () -> Unit,
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    listOf(Palette.PaperDeep.copy(alpha = 0f), Palette.PaperShade),
                ),
            )
            .padding(start = 18.dp, end = 18.dp, top = 22.dp, bottom = 18.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AnimatedVisibility(
            visible = count > 0,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically(),
        ) {
            SealButton("選び直す", primary = false, onClick = onClear)
        }
        Spacer(Modifier.weight(1f))
        SealButton(
            text = when {
                count > 0 -> "この部位に留める（$count）"
                hadBinding -> "留めたものをはずす"
                else -> "閉じる"
            },
            primary = count > 0,
            onClick = onConfirm,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SealButton(text: String, primary: Boolean, onClick: () -> Unit) {
    Text(
        text = text,
        style = TextStyle(
            fontFamily = serif,
            fontSize = 15.sp,
            color = if (primary) Palette.Cream else Palette.Ink,
        ),
        modifier = Modifier
            .clip(RoundedCornerShape(3.dp))
            .combinedClickable(onClick = onClick)
            .drawBehind {
                drawRect(if (primary) Palette.Ink else Palette.Cream)
                drawRect(Palette.Ink.copy(alpha = 0.55f), style = Stroke(width = 1.4f))
                // 内側にもう 1 本。銅版の罫囲みの作法。
                val i = 3f
                drawRect(
                    color = (if (primary) Palette.Cream else Palette.Ink).copy(alpha = 0.35f),
                    topLeft = Offset(i, i),
                    size = Size(size.width - i * 2, size.height - i * 2),
                    style = Stroke(width = 0.9f),
                )
            }
            .padding(horizontal = 18.dp, vertical = 11.dp),
    )
}

/** 銅版の罫囲み。外に太く、内に細く。 */
private fun Modifier.ruledBorder() = drawBehind {
    drawRect(Palette.Ink.copy(alpha = 0.55f), style = Stroke(width = 1.5f))
    val i = 4f
    drawRect(
        color = Palette.Ink.copy(alpha = 0.28f),
        topLeft = Offset(i, i),
        size = Size(size.width - i * 2, size.height - i * 2),
        style = Stroke(width = 0.9f),
    )
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
        textStyle = TextStyle(fontFamily = serif, fontSize = 16.sp, color = Palette.Ink),
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
                            fontFamily = serif, fontSize = 16.sp, fontStyle = FontStyle.Italic,
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
                    drawCircle(Palette.Ink.copy(alpha = 0.55f), r, c, style = Stroke(width = 1.6f))
                    drawCircle(Palette.Ink.copy(alpha = 0.22f), r * 0.90f, c, style = Stroke(width = 1f))
                    if (order > 0) {
                        drawCircle(Palette.Ink.copy(alpha = 0.10f), r, c)
                        drawCircle(Palette.Ink, r, c, style = Stroke(width = 2.6f))
                    }
                },
            )
            Image(bitmap = app.icon, contentDescription = app.label, modifier = Modifier.size(40.dp))

            // 何番目に留めたかは、名前ではなく印で示す。名前の頭につけると
            // 長いアプリ名で切れて読めなくなる。
            if (order > 0) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(21.dp)
                        .drawBehind {
                            val r = size.minDimension / 2f
                            val c = Offset(size.width / 2f, size.height / 2f)
                            drawCircle(Palette.Ink, r, c)
                            drawCircle(Palette.Cream, r, c, style = Stroke(width = 1.2f))
                        },
                ) {
                    Text("$order", style = body(11, Palette.Cream))
                }
            }
        }
        Spacer(Modifier.height(6.dp))
        Text(
            text = app.label,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            style = body(11, Palette.Ink),
        )
    }
}
