package com.botanical.launcher.ui

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
import com.botanical.launcher.garden.Palette
import com.botanical.launcher.garden.Plate
import com.botanical.launcher.garden.PlantLayer
import kotlin.math.pow
import kotlin.math.sin

/**
 * 版面の描画。
 *
 * 切り抜いた株を丸ごと回転させると板を振っているように見えるので、
 * `drawBitmapMesh` で根元を固定したまま上ほど大きく撓ませる。
 * 風に揺れる草の見え方はこれでほぼ再現できる。
 */
@Composable
fun PlateCanvas(
    plate: Plate,
    transform: SceneTransform,
    phase: () -> Float,
    bloom: () -> Float,
    nudgedPlantId: () -> String?,
    nudgeAmount: () -> Float,
    bindings: Map<String, String>,
    appsByKey: Map<String, AppEntry>,
    showCaptions: Boolean,
    showHitAreas: Boolean,
    modifier: Modifier = Modifier,
) {
    val measurer = rememberTextMeasurer()
    val paint = remember { Paint().apply { isFilterBitmap = true; isAntiAlias = true; isDither = true } }

    Canvas(modifier) {
        drawPaper(plate)
        if (transform.scale <= 0f) return@Canvas

        if (plate.frame) drawPlateFrame(plate, transform)

        val p = phase()
        val nudged = nudgedPlantId()
        val nudge = nudgeAmount()
        val b = bloom()
        for (layer in plate.plants) {
            val bitmap = layer.bitmap ?: continue
            drawBent(layer, bitmap, transform, p, if (layer.id == nudged) nudge else 0f, paint)
        }

        // 蕾の上に開いた花を重ねて咲かせる
        val gemma = plate.gemma
        val openBitmap = gemma?.openBitmap
        if (gemma?.rect != null && openBitmap != null && b > 0.004f) {
            val grow = 0.34f + 0.66f * b
            val center = transform.toScreen(gemma.rect.center)
            val w = gemma.rect.width * transform.scale * grow
            val h = gemma.rect.height * transform.scale * grow
            paint.alpha = (b * 255).toInt().coerceIn(0, 255)
            drawContext.canvas.nativeCanvas.drawBitmap(
                openBitmap,
                android.graphics.Rect(0, 0, openBitmap.width, openBitmap.height),
                android.graphics.RectF(
                    center.x - w / 2f,
                    center.y - h / 2f,
                    center.x + w / 2f,
                    center.y + h / 2f,
                ),
                paint,
            )
            paint.alpha = 255
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
 * 根元を固定した撓み。頂部ほど振れるよう、根元からの距離の 1.7 乗で効かせる。
 * [grow] は開花時にわずかに持ち上げるための拡大。
 */
private fun DrawScope.drawBent(
    layer: PlantLayer,
    bitmap: android.graphics.Bitmap,
    t: SceneTransform,
    phase: Float,
    extra: Float,
    paint: Paint,
    grow: Float = 0f,
) {
    val cols = 3
    val rows = 12
    val topLeft = t.toScreen(layer.rect.topLeft)
    val w = layer.rect.width * t.scale * (1f + grow * 0.06f)
    val h = layer.rect.height * t.scale * (1f + grow * 0.06f)
    val left = topLeft.x - (w - layer.rect.width * t.scale) * layer.pivot.x
    val top = topLeft.y - (h - layer.rect.height * t.scale) * layer.pivot.y

    val swing = sin(phase * layer.bendSpeed + layer.bendPhase)
    val amplitude = layer.bendAmplitude * t.scale * (1f + extra * 2.2f)
    val pivotY = layer.pivot.y.coerceIn(0.05f, 1f)

    val verts = FloatArray((cols + 1) * (rows + 1) * 2)
    var i = 0
    for (r in 0..rows) {
        val v = r / rows.toFloat()
        // 根元で 0、先端で 1
        val u = ((pivotY - v) / pivotY).coerceIn(0f, 1f)
        val dx = amplitude * u.pow(1.7f) * swing
        for (c in 0..cols) {
            verts[i++] = left + (c / cols.toFloat()) * w + dx
            verts[i++] = top + v * h
        }
    }
    drawContext.canvas.nativeCanvas.drawBitmapMesh(bitmap, cols, rows, verts, 0, null, 0, paint)
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
    val topLeft = t.toScreen(Offset(inset, inset))
    val frameSize = Size((plate.width - inset * 2) * t.scale, (plate.height - inset * 2) * t.scale)
    drawRect(
        color = Palette.Ink.copy(alpha = 0.5f),
        topLeft = topLeft,
        size = frameSize,
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

/** 割り当て済みの器官にだけアプリ名を添える。 */
private fun DrawScope.drawBindingLabels(
    measurer: TextMeasurer,
    t: SceneTransform,
    plate: Plate,
    bindings: Map<String, String>,
    appsByKey: Map<String, AppEntry>,
) {
    if (bindings.isEmpty() || t.scale <= 0f) return
    for ((organId, appKey) in bindings) {
        val app = appsByKey[appKey] ?: continue
        val (layer, organ) = plate.organ(organId) ?: continue
        val layout = measurer.measure(AnnotatedString(app.label), labelStyle, maxLines = 1)
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
