package c.core.ex

import android.graphics.Canvas
import android.graphics.Paint
import kotlin.math.abs

/**
 * 居中绘制多行文本
 * 需要先translate至中心点
 */
fun Canvas.drawCenterMultiText(p: Paint, texts: Array<String>) {
    val fontMetrics: Paint.FontMetrics = p.fontMetrics
    val top = abs(fontMetrics.top)
    val ascent = abs(fontMetrics.ascent)
    val descent = fontMetrics.descent
    val bottom = fontMetrics.bottom
    val textLines = texts.size
    val textHeight = top + bottom
    val textTotalHeight = textHeight * textLines
    val basePosition = (textLines - 1) / 2f
    for (i in 0 until textLines) {
        val textWidth: Float = p.measureText(texts[i])
        val baselineY: Float = when {
            i < basePosition -> {
                -(textTotalHeight / 2 - textHeight * i - top)
            }
            i > basePosition -> {
                textTotalHeight / 2 - textHeight * (textLines - i - 1) - bottom
            }

            else -> (ascent - descent) / 2

        }
        this.drawText(texts[i], -textWidth / 2, baselineY, p)
    }
}

/**
 * 居中绘制文本
 * 需要先translate至中心点
 */
fun Canvas.drawCenterText(p: Paint, text: String) {
    val fontMetrics: Paint.FontMetrics = p.fontMetrics
    val ascent = abs(fontMetrics.ascent)
    val descent = fontMetrics.descent
    val textWidth: Float = p.measureText(text)
    val baselineY: Float = (ascent - descent) / 2
    this.drawText(text, -textWidth / 2, baselineY, p)
}
