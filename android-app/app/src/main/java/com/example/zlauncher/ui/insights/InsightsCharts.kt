package com.example.zlauncher.ui.insights

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zlauncher.core.designsystem.ZColors
import com.example.zlauncher.core.designsystem.ZMotion
import com.example.zlauncher.core.designsystem.ZType
import com.example.zlauncher.core.ui.formatBytes
import com.example.zlauncher.core.ui.springyClick
import com.example.zlauncher.domain.model.CategoryRollup
import com.example.zlauncher.domain.model.NO_COLOR_INDEX
import com.example.zlauncher.domain.model.OTHER_COLOR_INDEX

/**
 * Web Insights のグラフ部品。
 *
 * 守っている決まり:
 * - 系列の色は必ず [ZColors.CategoryChartColors]。カテゴリーが自分で持つ色番号に従い、
 *   並び順（多い順）では決めない。絞り込みで系列が減っても残りの色は変わらない
 * - 積み上げの各段には 2dp の隙間を空ける。隣り合う色が地続きだと境目が読めない
 * - 数字とラベルは必ずテキスト色。系列色を文字に使うと、色の意味が二重になる
 * - 2 系列以上あるときは凡例を必ず出す。色だけで区別させない
 */

/** 系列色。色番号を持たないもの（未分類・その他）は無彩色に落とす */
fun chartColor(colorIndex: Int): Color = when (colorIndex) {
    OTHER_COLOR_INDEX -> ZColors.ChartOther
    NO_COLOR_INDEX -> ZColors.ChartOther
    else -> ZColors.CategoryChartColors[colorIndex % ZColors.CategoryChartColors.size]
}

/** 識別色（点・レール・枠に使う明るいほう） */
fun identityColor(colorIndex: Int): Color =
    if (colorIndex < 0) ZColors.TextDim else ZColors.CategoryColors[colorIndex % ZColors.CategoryColors.size]

private val BAR_CORNER = 3.dp
private val SEGMENT_GAP = 2.dp

/** 行内ミニグラフの最大本数 */
private const val MINI_BARS = 16

/**
 * カテゴリー別の積み上げ時系列。
 *
 * タップでその時間帯に絞り込む。触って選ぶ画面なのでホバーは無く、選択中の棒の内訳は
 * 呼び出し側が読み取り欄として出す。
 */
@Composable
fun StackedTimeline(
    bucketStarts: List<Long>,
    series: List<CategoryRollup>,
    selected: Long?,
    onSelect: (Long) -> Unit,
    modifier: Modifier = Modifier,
    height: androidx.compose.ui.unit.Dp = 96.dp,
) {
    val totals = bucketStarts.indices.map { index ->
        series.sumOf { it.series.getOrElse(index) { 0L } }
    }
    val peak = totals.maxOrNull() ?: 0L

    Row(
        modifier.fillMaxWidth().height(height),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.Bottom,
    ) {
        bucketStarts.forEachIndexed { index, start ->
            StackedBar(
                modifier = Modifier.weight(1f),
                values = series.map { it.series.getOrElse(index) { 0L } },
                colors = series.map { chartColor(it.colorIndex) },
                total = totals[index],
                peak = peak,
                selected = selected == start,
                dimmed = selected != null && selected != start,
                onClick = { onSelect(start) },
            )
        }
    }
}

@Composable
private fun StackedBar(
    modifier: Modifier,
    values: List<Long>,
    colors: List<Color>,
    total: Long,
    peak: Long,
    selected: Boolean,
    dimmed: Boolean,
    onClick: () -> Unit,
) {
    // 0 のバケットでも掴めるように土台を残す。棒の意味は高さなので、土台は線として引く
    val fraction = if (peak <= 0) 0f else total.toFloat() / peak.toFloat()
    val barHeight by animateFloatAsState(
        targetValue = fraction.coerceIn(0f, 1f) * 0.96f + 0.04f,
        animationSpec = ZMotion.value(),
        label = "stackHeight",
    )
    val alpha by animateFloatAsState(
        targetValue = if (dimmed) 0.35f else 1f,
        animationSpec = ZMotion.value(),
        label = "stackAlpha",
    )

    Box(
        modifier.fillMaxHeight().springyClick(onClick = onClick),
        contentAlignment = Alignment.BottomCenter,
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(barHeight),
            verticalArrangement = Arrangement.Bottom,
        ) {
            if (total <= 0) {
                Box(Modifier.fillMaxWidth().fillMaxHeight().clip(RoundedCornerShape(1.dp)).background(ZColors.Outline))
            } else {
                // 上が最初の系列。上端だけ角を丸め、段の間に 2dp の隙間を入れる
                values.forEachIndexed { index, value ->
                    if (value <= 0) return@forEachIndexed
                    val weight = value.toFloat() / total.toFloat()
                    val isTop = values.take(index).all { it <= 0 }
                    // 隙間は段と段のあいだにだけ。最下段に付けると棒が土台から浮いて見える
                    val isBottom = values.drop(index + 1).all { it <= 0 }
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .weight(weight)
                            .padding(bottom = if (isBottom) 0.dp else SEGMENT_GAP)
                            .clip(
                                if (isTop) {
                                    RoundedCornerShape(topStart = BAR_CORNER, topEnd = BAR_CORNER)
                                } else {
                                    RoundedCornerShape(0.dp)
                                }
                            )
                            .background(colors.getOrElse(index) { ZColors.ChartOther }.copy(alpha = alpha))
                    )
                }
            }
        }
        if (selected) {
            // 選択中は下に指標を置く。棒自体を明るくすると系列色の意味が壊れる
            Box(
                Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(ZColors.TextPrimary)
            )
        }
    }
}

/**
 * 凡例。2 系列以上なら必ず出す。色の隣に名前と実数を並べるので、
 * 色が見分けられない人でもどれがどれか読める。
 */
@Composable
fun ChartLegend(
    series: List<CategoryRollup>,
    modifier: Modifier = Modifier,
    onClick: ((CategoryRollup) -> Unit)? = null,
) {
    Column(modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(5.dp)) {
        series.forEach { rollup ->
            val (value, unit) = formatBytes(rollup.totalBytes)
            Row(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(6.dp))
                    .let { if (onClick != null) it.springyClick { onClick(rollup) } else it }
                    .padding(vertical = 2.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(7.dp),
            ) {
                Box(
                    Modifier
                        .size(9.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(chartColor(rollup.colorIndex))
                )
                Text(
                    text = rollup.name,
                    style = ZType.Body,
                    color = ZColors.TextSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f),
                )
                Text("$value $unit", style = ZType.UnitText, color = ZColors.TextPrimary)
            }
        }
    }
}

/** 全体に占める割合の横棒。系列色で塗り、数字はテキスト色で置く */
@Composable
fun ShareBar(
    fraction: Float,
    color: Color,
    modifier: Modifier = Modifier,
    height: androidx.compose.ui.unit.Dp = 4.dp,
) {
    val width by animateFloatAsState(
        targetValue = fraction.coerceIn(0f, 1f),
        animationSpec = ZMotion.value(),
        label = "shareWidth",
    )
    Box(
        modifier
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(999.dp))
            .background(ZColors.StatusNeutral),
    ) {
        Box(
            Modifier
                .fillMaxWidth(width)
                .fillMaxHeight()
                .clip(RoundedCornerShape(999.dp))
                .background(color)
        )
    }
}

/**
 * 受信 / 送信、Wi-Fi / モバイルのような 2 値の内訳。
 * 軸は 1 本（バイト数）だけ。単位の違うものを同じ棒に混ぜない。
 */
@Composable
fun SplitBar(
    leftLabel: String,
    leftBytes: Long,
    leftColor: Color,
    rightLabel: String,
    rightBytes: Long,
    rightColor: Color,
    modifier: Modifier = Modifier,
) {
    val total = (leftBytes + rightBytes).coerceAtLeast(1)
    val leftShare by animateFloatAsState(
        targetValue = leftBytes.toFloat() / total.toFloat(),
        animationSpec = ZMotion.value(),
        label = "splitShare",
    )
    val (leftValue, leftUnit) = formatBytes(leftBytes)
    val (rightValue, rightUnit) = formatBytes(rightBytes)

    Column(modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Row(
            Modifier.fillMaxWidth().height(6.dp),
            horizontalArrangement = Arrangement.spacedBy(SEGMENT_GAP),
        ) {
            if (leftShare > 0f) {
                Box(
                    Modifier
                        .weight(leftShare.coerceAtLeast(0.001f))
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(999.dp))
                        .background(leftColor)
                )
            }
            if (leftShare < 1f) {
                Box(
                    Modifier
                        .weight((1f - leftShare).coerceAtLeast(0.001f))
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(999.dp))
                        .background(rightColor)
                )
            }
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(14.dp)) {
            SplitLabel(leftLabel, "$leftValue $leftUnit", leftColor)
            SplitLabel(rightLabel, "$rightValue $rightUnit", rightColor)
        }
    }
}

@Composable
private fun SplitLabel(label: String, value: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(5.dp)) {
        Box(Modifier.size(7.dp).clip(CircleShape).background(color))
        Text(label, style = ZType.Sub, color = ZColors.TextSecondary)
        Text(value, style = ZType.UnitText.copy(fontSize = 11.sp), color = ZColors.TextPrimary)
    }
}

/** 行の中に置く小さな時系列。値の読み取りではなく、山がどこにあるかを見るためのもの */
@Composable
fun MiniSeries(
    series: List<Long>,
    color: Color,
    modifier: Modifier = Modifier,
    height: androidx.compose.ui.unit.Dp = 20.dp,
) {
    val peak = series.maxOrNull() ?: 0L
    Row(
        modifier.height(height).width(64.dp),
        horizontalArrangement = Arrangement.spacedBy(1.dp),
        verticalAlignment = Alignment.Bottom,
    ) {
        // 64dp に 28 本は入らない（1 本 1dp 強で線に潰れる）。
        // 16 本を上限にまとめて、1 本あたり 3dp 以上を確保する
        val step = maxOf(1, (series.size + MINI_BARS - 1) / MINI_BARS)
        series.chunked(step).forEach { chunk ->
            val value = chunk.sum()
            val fraction = if (peak <= 0) 0f else (value.toFloat() / (peak * step).toFloat())
            Box(
                Modifier
                    .weight(1f)
                    .fillMaxHeight(fraction.coerceIn(0f, 1f) * 0.9f + 0.1f)
                    .clip(RoundedCornerShape(topStart = 1.dp, topEnd = 1.dp))
                    .background(if (value > 0) color else ZColors.Outline)
            )
        }
    }
}

/** 値の無い状態。空白のままにすると読み込み中と区別がつかない */
@Composable
fun ChartEmpty(text: String, modifier: Modifier = Modifier) {
    Box(modifier.fillMaxWidth().height(72.dp), contentAlignment = Alignment.Center) {
        Text(text, style = ZType.Body, color = ZColors.TextSecondary)
    }
}

@Composable
fun ChartSpacer(height: androidx.compose.ui.unit.Dp) {
    Spacer(Modifier.height(height))
}
