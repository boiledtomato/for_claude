package com.botanical.launcher.ui

import android.graphics.Bitmap
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.sp
import com.botanical.launcher.data.AppEntry
import com.botanical.launcher.garden.Gemma
import com.botanical.launcher.garden.Palette
import com.botanical.launcher.garden.Plate
import com.botanical.launcher.garden.PlantLayer

/**
 * 版面の描画。
 *
 * アニメーションする値（[phase] [bloom] [nudgeAmount]）はすべて draw ラムダの中で
 * 読むので、再コンポーズは起きず再描画だけで済む。
 */
@Composable
fun PlateCanvas(
    plate: Plate,
    transform: SceneTransform,
    phase: () -> Float,
    bloom: () -> Float,
    nudgedPlantId: () -> String?,
    nudgeAmount: () -> Float,
    bindings: Map<String, List<String>>,
    appsByKey: Map<String, AppEntry>,
    showCaptions: Boolean,
    showHitAreas: Boolean,
    modifier: Modifier = Modifier,
) {
    val measurer = rememberTextMeasurer()
    val paint = remember {
        Paint().apply { isFilterBitmap = true; isAntiAlias = true; isDither = true }
    }
    // 風の場は版面ごとに一度だけ組む
    val winds = remember(plate) { plate.plants.associate { it.id to WindField.of(plate, it) } }

    Canvas(modifier) {
        drawPaper(plate)
        if (transform.scale <= 0f) return@Canvas
        if (plate.frame) drawPlateFrame(plate, transform)

        val p = phase()
        val nudged = nudgedPlantId()
        val nudge = nudgeAmount()
        val b = bloom()
        val gust = WindField.gust(p)

        val gemma = plate.gemma
        val gemmaHost = gemma?.let { plate.organ(it.organId) }
        val budAt = gemmaHost?.let { it.second.at.x to it.second.at.y }

        for (layer in plate.plants) {
            val bitmap = layer.bitmap ?: continue
            val wind = winds[layer.id] ?: continue
            val isHost = gemmaHost?.first?.id == layer.id
            drawWindblown(
                layer = layer,
                bitmap = bitmap,
                wind = wind,
                t = transform,
                phase = p,
                gust = gust,
                nudge = if (layer.id == nudged) nudge else 0f,
                bloom = if (isHost) b else 0f,
                bloomAt = if (isHost) budAt else null,
                paint = paint,
            )
        }

        // 蕾の上に、花弁がほどけるように開いた花を重ねる
        val openBitmap = gemma?.openBitmap
        if (gemma?.rect != null && openBitmap != null && b > 0.004f && gemmaHost != null) {
            drawBloom(
                gemma = gemma,
                bitmap = openBitmap,
                host = gemmaHost.first,
                wind = winds[gemmaHost.first.id],
                t = transform,
                phase = p,
                gust = gust,
                bloom = b,
                paint = paint,
            )
        }

        if (showCaptions) {
            for (layer in plate.plants) drawCaption(measurer, transform, layer)
            drawPlateNumber(measurer, transform, plate)
        }
        drawBindingLabels(measurer, transform, plate, bindings, appsByKey)
        if (showHitAreas) drawHitAreas(transform, plate)
    }
}

/**
 * 風で揺れる版面。
 *
 * 全体を一様に撓ませるとただの板が揺れているようにしか見えない。メッシュを
 * 細かく取り、頂点ごとに [WindField] を評価することで、隣り合う花が別々の位相で
 * 頷き、突風が横切っていくように見える。
 */
private fun DrawScope.drawWindblown(
    layer: PlantLayer,
    bitmap: Bitmap,
    wind: WindField,
    t: SceneTransform,
    phase: Float,
    gust: Float,
    nudge: Float,
    bloom: Float,
    bloomAt: Pair<Float, Float>?,
    paint: Paint,
) {
    val cols = 12
    val rows = 20
    val topLeft = t.toScreen(layer.rect.topLeft)
    val w = layer.rect.width * t.scale
    val h = layer.rect.height * t.scale

    val verts = FloatArray((cols + 1) * (rows + 1) * 2)
    val d = FloatArray(2)
    var i = 0
    for (r in 0..rows) {
        val v = r / rows.toFloat()
        for (c in 0..cols) {
            val u = c / cols.toFloat()
            wind.displace(u, v, phase, gust, nudge, bloom, bloomAt, d)
            verts[i++] = topLeft.x + u * w + d[0] * t.scale
            verts[i++] = topLeft.y + v * h + d[1] * t.scale
        }
    }
    drawContext.canvas.nativeCanvas.drawBitmapMesh(bitmap, cols, rows, verts, 0, null, 0, paint)
}

/**
 * 開花。花弁が中心からほどけて広がるように、スプライトをメッシュで変形する。
 * 単なるクロスフェードでは「開いた」ようには見えない。
 */
private fun DrawScope.drawBloom(
    gemma: Gemma,
    bitmap: Bitmap,
    host: PlantLayer,
    wind: WindField?,
    t: SceneTransform,
    phase: Float,
    gust: Float,
    bloom: Float,
    paint: Paint,
) {
    val rect = gemma.rect ?: return
    val eased = bloom * bloom * (3f - 2f * bloom)

    // 蕾と同じだけ風に流される。でないと咲く途中で花だけ取り残される。
    val d = FloatArray(2)
    if (wind != null && host.rect.height > 0f && host.rect.width > 0f) {
        wind.displace(
            u = (rect.center.x - host.rect.left) / host.rect.width,
            v = (rect.center.y - host.rect.top) / host.rect.height,
            phase = phase, gust = gust, nudge = 0f, bloom = 0f, bloomAt = null, out = d,
        )
    }
    val centre = t.toScreen(rect.center)
    val cx = centre.x + d[0] * t.scale
    val cy = centre.y + d[1] * t.scale
    val w = rect.width * t.scale
    val h = rect.height * t.scale

    val cols = 8
    val rows = 8
    val verts = FloatArray((cols + 1) * (rows + 1) * 2)
    val uv = FloatArray(2)
    var i = 0
    for (r in 0..rows) {
        for (c in 0..cols) {
            unfurl(c / cols.toFloat(), r / rows.toFloat(), eased, uv)
            verts[i++] = cx + (uv[0] - 0.5f) * w
            verts[i++] = cy + (uv[1] - 0.5f) * h
        }
    }
    paint.alpha = (((bloom - 0.04f) / 0.30f).coerceIn(0f, 1f) * 255).toInt()
    drawContext.canvas.nativeCanvas.drawBitmapMesh(bitmap, cols, rows, verts, 0, null, 0, paint)
    paint.alpha = 255
}

private fun DrawScope.drawPaper(plate: Plate) {
    drawRect(
        brush = Brush.linearGradient(
            colors = listOf(plate.paper, plate.paperDeep, plate.paper),
            start = Offset.Zero,
            end = Offset(size.width, size.height),
        ),
    )
    drawRect(
        brush = Brush.radialGradient(
            colors = listOf(Color.Transparent, Palette.PaperShade.copy(alpha = 0.5f)),
            center = Offset(size.width / 2f, size.height / 2f),
            radius = maxOf(size.width, size.height) * 0.72f,
        ),
    )
}

private fun DrawScope.drawPlateFrame(plate: Plate, t: SceneTransform) {
    val inset = 36f
    drawRect(
        color = Palette.Ink.copy(alpha = 0.5f),
        topLeft = t.toScreen(Offset(inset, inset)),
        size = Size((plate.width - inset * 2) * t.scale, (plate.height - inset * 2) * t.scale),
        style = Stroke(width = 2.4f * t.scale),
    )
    val inner = inset + 9f
    drawRect(
        color = Palette.Ink.copy(alpha = 0.32f),
        topLeft = t.toScreen(Offset(inner, inner)),
        size = Size((plate.width - inner * 2) * t.scale, (plate.height - inner * 2) * t.scale),
        style = Stroke(width = 1.1f * t.scale),
    )
}

private val captionStyle = TextStyle(
    fontFamily = FontFamily.Serif,
    fontSize = 10.sp,
    fontStyle = FontStyle.Italic,
    color = Palette.Ink.copy(alpha = 0.82f),
)

private val labelStyle = TextStyle(
    fontFamily = FontFamily.Serif,
    fontSize = 11.sp,
    color = Palette.Ink,
)

private fun DrawScope.drawCaption(measurer: TextMeasurer, t: SceneTransform, layer: PlantLayer) {
    if (layer.latin.isBlank()) return
    val at = layer.captionAt ?: Offset(
        layer.rect.left + layer.rect.width * layer.pivot.x,
        layer.rect.bottom + 26f,
    )
    val text = if (layer.number.isBlank()) layer.latin else "${layer.number} ${layer.latin}"
    val layout = measurer.measure(AnnotatedString(text), captionStyle)
    val p = t.toScreen(at)
    drawText(layout, topLeft = Offset(p.x - layout.size.width / 2f, p.y))
}

private fun DrawScope.drawPlateNumber(measurer: TextMeasurer, t: SceneTransform, plate: Plate) {
    if (plate.number.isBlank()) return
    val layout = measurer.measure(
        AnnotatedString(plate.number),
        captionStyle.copy(fontSize = 13.sp, fontStyle = FontStyle.Normal),
    )
    val at = t.toScreen(Offset(plate.width - 70f, 52f))
    drawText(layout, topLeft = Offset(at.x - layout.size.width, at.y))
}

/**
 * 割り当て済みの部位にだけ名前を添える。複数入っているときは
 * 代表 1 つと残りの数を出す。
 */
private fun DrawScope.drawBindingLabels(
    measurer: TextMeasurer,
    t: SceneTransform,
    plate: Plate,
    bindings: Map<String, List<String>>,
    appsByKey: Map<String, AppEntry>,
) {
    if (bindings.isEmpty() || t.scale <= 0f) return
    for ((organId, keys) in bindings) {
        val present = keys.mapNotNull { appsByKey[it] }
        if (present.isEmpty()) continue
        val (layer, organ) = plate.organ(organId) ?: continue
        val text = if (present.size == 1) {
            present[0].label
        } else {
            "${present[0].label} +${present.size - 1}"
        }
        val layout = measurer.measure(AnnotatedString(text), labelStyle, maxLines = 1)
        val center = t.toScreen(organ.center(layer))
        val y = center.y + organ.radius(layer) * t.scale + 6f
        val x = center.x - layout.size.width / 2f
        val pad = 5f
        drawRoundRect(
            color = Palette.Paper.copy(alpha = 0.88f),
            topLeft = Offset(x - pad, y - 2f),
            size = Size(layout.size.width + pad * 2, layout.size.height + 4f),
            cornerRadius = CornerRadius(6f, 6f),
        )
        drawLine(
            color = Palette.Ink.copy(alpha = 0.35f),
            start = center,
            end = Offset(center.x, y - 2f),
            strokeWidth = 1f,
        )
        drawText(layout, topLeft = Offset(x, y))
    }
}

/** 当たり判定の可視化。座標を詰めるときだけ使う開発用。 */
private fun DrawScope.drawHitAreas(t: SceneTransform, plate: Plate) {
    for ((layer, organ) in plate.tapTargets) {
        drawCircle(
            color = Palette.Crimson.copy(alpha = 0.45f),
            radius = organ.radius(layer) * t.scale,
            center = t.toScreen(organ.center(layer)),
            style = Stroke(width = 2f),
        )
    }
}
