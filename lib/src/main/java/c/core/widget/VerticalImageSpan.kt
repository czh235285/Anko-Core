package c.core.widget

/**
 * Created by admin on 2018/1/22.
 */

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.style.ImageSpan

/**
 * 垂直居中的ImageSpan
 */
class VerticalImageSpan(drawable: Drawable) : ImageSpan(drawable) {

    override fun getSize(paint: Paint, text: CharSequence, start: Int, end: Int,
                         fontMetricsInt: Paint.FontMetricsInt?): Int {
        val drawable = drawable
        val rect = drawable.bounds
        if (fontMetricsInt != null) {
            val fmPaint = paint.fontMetricsInt
            val fontHeight = fmPaint.bottom - fmPaint.top
            val drHeight = rect.bottom - rect.top

            val top = drHeight / 2 - fontHeight / 4
            val bottom = drHeight / 2 + fontHeight / 4

            fontMetricsInt.ascent = -bottom
            fontMetricsInt.top = -bottom
            fontMetricsInt.bottom = top
            fontMetricsInt.descent = top
        }
        return rect.right
    }

    override fun draw(canvas: Canvas, text: CharSequence, start: Int, end: Int,
                      x: Float, top: Int, y: Int, bottom: Int, paint: Paint) {
        val drawable = drawable
        canvas.save()
        var transY = 0
        transY = (bottom - top - drawable.bounds.bottom) / 2 + top
        canvas.translate(x, transY.toFloat())
        drawable.draw(canvas)
        canvas.restore()
    }
}