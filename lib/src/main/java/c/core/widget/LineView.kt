package c.core.widget

import android.content.Context
import android.graphics.Canvas
import android.view.View
import android.view.ViewManager
import c.core.utils.color
import org.jetbrains.anko.custom.ankoView


inline fun ViewManager.line(
    textColor: String? = null,
    theme: Int = 0,
    init: LineView.() -> Unit
): LineView {
    return ankoView({
        LineView(it).apply {
            textColor?.let {
                bgColor = textColor.color
            }
        }
    }, theme, init)
}

class LineView : View {

    var bgColor: Int = "#e5e5e5".color
        set(value) {
            field = value
            invalidate()
        }

    constructor(context: Context?) : super(context)


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawColor(bgColor)
    }
}