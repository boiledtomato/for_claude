package com.example.critterhome.ui

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.critterhome.game.MonsterSprite
import kotlinx.coroutines.delay

/**
 * 携帯ゲーム機の RPG 風の部品。色はすべて [Game.colors] から取るので、明るい / 暗い配色に
 * そのまま従う。フォントは同梱せず等幅の太字で寄せる。
 */
object RetroType {
    val Title = TextStyle(fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, fontSize = 15.sp, lineHeight = 20.sp)
    val Body = TextStyle(fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, fontSize = 12.sp, lineHeight = 17.sp)
    val Small = TextStyle(fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Medium, fontSize = 10.sp, lineHeight = 14.sp)
}

/** 二重枠のウィンドウ。外は太く濃く、内側に細い線をもう 1 本 */
@Composable
fun RetroWindow(
    modifier: Modifier = Modifier,
    padding: Dp = 10.dp,
    onClick: (() -> Unit)? = null,
    onLongClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    val outer = RoundedCornerShape(6.dp)
    Column(
        modifier
            .clip(outer)
            .background(Game.colors.window)
            .border(2.dp, Game.colors.ink, outer)
            .then(if (onClick != null) Modifier.pressable(onLongClick = onLongClick, onClick = onClick) else Modifier)
            .padding(3.dp)
            .border(1.dp, Game.colors.frameInner, RoundedCornerShape(3.dp))
            .padding(padding),
        content = content,
    )
}

/**
 * 生成したドット絵を描く。
 *
 * @param silhouette まだ出会っていない ― 影だけを見せる
 * @param fainted ひんし ― 体を灰色に落とす
 */
@Composable
fun PixelSprite(
    grid: List<IntArray>,
    color: Color,
    size: Dp,
    modifier: Modifier = Modifier,
    silhouette: Boolean = false,
    fainted: Boolean = false,
) {
    // DrawScope の中ではトークンを読めないので、ここで色を決めて渡す
    val ink = Game.colors.ink
    val dim = Game.colors.inkDim
    val gray = Game.colors.faint
    val body = if (fainted) gray else color
    val accent = lerp(body, ink, 0.35f)
    val shine = Game.colors.window
    Canvas(modifier.size(size)) {
        val n = grid.size
        if (n == 0) return@Canvas
        val cell = this.size.minDimension / n
        // 隣り合うマスの継ぎ目に隙間が出ないよう、ほんの少し大きく塗る
        val paint = Size(cell + 0.5f, cell + 0.5f)
        grid.forEachIndexed { r, row ->
            row.forEachIndexed { c, v ->
                val fill = when {
                    v == MonsterSprite.EMPTY -> null
                    silhouette -> dim
                    v == MonsterSprite.BODY -> body
                    v == MonsterSprite.ACCENT -> accent
                    v == MonsterSprite.EYE -> ink
                    else -> ink
                } ?: return@forEachIndexed
                drawRect(fill, topLeft = Offset(c * cell, r * cell), size = paint)
                if (v == MonsterSprite.EYE && !silhouette) {
                    drawRect(shine, topLeft = Offset(c * cell + cell * 0.15f, r * cell + cell * 0.15f), size = Size(cell * 0.35f, cell * 0.35f))
                }
            }
        }
    }
}

/** HP の色。残り半分で黄、2 割で赤 */
@Composable
fun hpColor(fraction: Float): Color = when {
    fraction > 0.5f -> Game.colors.hpGood
    fraction > 0.2f -> Game.colors.hpMid
    else -> Game.colors.hpLow
}

/** 「HP」の札とゲージ */
@Composable
fun StatBar(
    label: String,
    fraction: Float,
    color: Color,
    modifier: Modifier = Modifier,
    height: Dp = 7.dp,
) {
    val shown by animateFloatAsState(fraction.coerceIn(0f, 1f), tween(550), label = "statBar")
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(label, style = RetroType.Small.copy(fontSize = 9.sp), color = Game.colors.accent)
        Spacer(Modifier.width(5.dp))
        Box(
            Modifier
                .weight(1f)
                .height(height)
                .clip(RoundedCornerShape(height / 2))
                .background(Game.colors.sunken)
                .border(1.dp, Game.colors.ink, RoundedCornerShape(height / 2)),
        ) {
            Box(
                Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(shown)
                    .padding(1.5.dp)
                    .clip(RoundedCornerShape(height / 2))
                    .background(color),
            )
        }
    }
}

/**
 * 画面下のメッセージウィンドウ。1 文字ずつ出し、押すと先に全文、もう一度で次へ。
 *
 * 文の中身が変わったら最初から打ち直す。同じ文が 2 回続いても（同じ相棒が続けて
 * レベルアップしたとき）[key] が違えば打ち直す。
 */
@Composable
fun MessageBox(
    text: String,
    key: Any,
    onAdvance: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var shown by remember(key) { mutableIntStateOf(0) }
    LaunchedEffect(key) {
        while (shown < text.length) {
            delay(TYPE_MS)
            shown++
        }
    }
    val done = shown >= text.length
    val blink = rememberInfiniteTransition(label = "cursor")
    val alpha by blink.animateFloat(1f, 0f, infiniteRepeatable(tween(450), RepeatMode.Reverse), label = "cursorAlpha")
    RetroWindow(
        modifier = modifier.fillMaxWidth(),
        padding = 12.dp,
        onClick = { if (done) onAdvance() else shown = text.length },
    ) {
        Box(Modifier.fillMaxWidth().height(52.dp)) {
            Text(text.take(shown), style = RetroType.Body, color = Game.colors.ink)
            if (done) {
                Text(
                    "▼",
                    style = RetroType.Small,
                    color = Game.colors.ink,
                    modifier = Modifier.align(Alignment.BottomEnd).alpha(alpha),
                )
            }
        }
    }
}

/** メニューの 1 項目。選んでいるものにだけ ▶ を付ける */
@Composable
fun CursorItem(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier
            .clip(RoundedCornerShape(4.dp))
            .pressable(onClick = onClick)
            .padding(horizontal = 2.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            "▶",
            style = RetroType.Small,
            color = Game.colors.ink,
            modifier = Modifier.alpha(if (selected) 1f else 0f),
        )
        Spacer(Modifier.width(2.dp))
        Text(
            text,
            style = RetroType.Body.copy(fontSize = 11.sp),
            color = if (selected) Game.colors.ink else Game.colors.inkSoft,
            maxLines = 1,
        )
    }
}

/**
 * バッジの紋章。8 角形を 9×9 のマスで描く。取っていないものは輪郭だけ。
 */
@Composable
fun BadgeEmblem(color: Color, earned: Boolean, size: Dp, modifier: Modifier = Modifier) {
    val ink = Game.colors.ink
    val dim = Game.colors.inkDim
    val shine = Game.colors.window
    Canvas(modifier.size(size)) {
        val n = BADGE_SHAPE.size
        val cell = this.size.minDimension / n
        val paint = Size(cell + 0.5f, cell + 0.5f)
        BADGE_SHAPE.forEachIndexed { r, row ->
            row.forEachIndexed { c, ch ->
                val fill = when (ch) {
                    'o' -> if (earned) ink else dim
                    'x' -> if (earned) color else null
                    '*' -> if (earned) shine else null
                    else -> null
                } ?: return@forEachIndexed
                drawRect(fill, topLeft = Offset(c * cell, r * cell), size = paint)
            }
        }
    }
}

private val BADGE_SHAPE = listOf(
    "..ooooo..",
    ".oxxxxxo.",
    "ox**xxxxo",
    "ox*xxxxxo",
    "oxxxxxxxo",
    "oxxxxxxxo",
    "oxxxxxxxo",
    ".oxxxxxo.",
    "..ooooo..",
)

private const val TYPE_MS = 28L

/**
 * 押せるもの全部に付ける。押している間だけ少し縮める ― 反応が見えないと、ホームの
 * タップが届いたのか分からない。長押しは [onLongClick] を渡したときだけ効く。
 */
@OptIn(ExperimentalFoundationApi::class)
fun Modifier.pressable(
    onLongClick: (() -> Unit)? = null,
    onClick: () -> Unit,
): Modifier = composed {
    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()
    val scale by animateFloatAsState(if (pressed) PRESS_SCALE else 1f, spring(dampingRatio = 0.7f), label = "press")
    this
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .combinedClickable(
            interactionSource = interaction,
            indication = null,
            onClick = onClick,
            onLongClick = onLongClick,
        )
}

private const val PRESS_SCALE = 0.95f
