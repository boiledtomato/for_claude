package com.pointille.launcher.paint

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF

/**
 * A small drawing helper so the motifs read the way they were drawn in the
 * prototype. Everything works in a 100x100 space; the caller scales it onto
 * the panel, so a motif never has to know how big the icon ends up.
 */
class Pen(private val c: Canvas) {

    private val solid = Paint(Paint.ANTI_ALIAS_FLAG).apply { style = Paint.Style.FILL }
    private val line = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
    }
    private val rect = RectF()
    private val path = Path()

    fun color(argb: Int) = apply { solid.color = argb }
    fun ink(argb: Int) = apply { line.color = argb }

    /** Rounded rectangle. */
    fun rr(x: Float, y: Float, w: Float, h: Float, r: Float) {
        rect.set(x, y, x + w, y + h)
        c.drawRoundRect(rect, r, r, solid)
    }

    fun ci(x: Float, y: Float, r: Float) = c.drawCircle(x, y, r, solid)

    fun oval(cx: Float, cy: Float, rx: Float, ry: Float, rot: Float = 0f) {
        rect.set(cx - rx, cy - ry, cx + rx, cy + ry)
        if (rot == 0f) {
            c.drawOval(rect, solid)
        } else {
            c.save(); c.rotate(Math.toDegrees(rot.toDouble()).toFloat(), cx, cy)
            c.drawOval(rect, solid); c.restore()
        }
    }

    fun tri(x1: Float, y1: Float, x2: Float, y2: Float, x3: Float, y3: Float) {
        path.reset()
        path.moveTo(x1, y1); path.lineTo(x2, y2); path.lineTo(x3, y3); path.close()
        c.drawPath(path, solid)
    }

    fun ln(x1: Float, y1: Float, x2: Float, y2: Float, w: Float) {
        line.strokeWidth = w
        c.drawLine(x1, y1, x2, y2, line)
    }

    fun arc(cx: Float, cy: Float, r: Float, startDeg: Float, sweepDeg: Float, w: Float) {
        line.strokeWidth = w
        rect.set(cx - r, cy - r, cx + r, cy + r)
        c.drawArc(rect, startDeg, sweepDeg, false, line)
    }

    /** A filled free-form shape, for the motifs that need curves. */
    fun shape(build: Path.() -> Unit) {
        path.reset(); path.build(); path.close()
        c.drawPath(path, solid)
    }

    /** A stroked free-form shape. */
    fun trace(w: Float, build: Path.() -> Unit) {
        line.strokeWidth = w
        path.reset(); path.build()
        c.drawPath(path, line)
    }
}
