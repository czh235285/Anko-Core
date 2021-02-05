package c.core.sample.utils

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**

 * Created  on 2018/5/20.

 * @author czh

 */

class SimpleDividerDecoration(
    dividerColor: Int,
    private val dividerHeight: Int,
    val leftPadding: Int = 0,
    val rightPadding: Int = 0,
    vararg val exclude: Int
) : RecyclerView.ItemDecoration() {

    val p = Paint().apply {
        color = dividerColor
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = dividerHeight
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {

        val childCount = parent.childCount

        val left = parent.paddingLeft

        val right = parent.width - parent.paddingRight

        (0 until childCount - 1).forEachIndexed { index, it ->
            if (!exclude.contains(index)) {
                val view = parent.getChildAt(it)
                val top = view.bottom.toFloat()
                val bottom = (view.bottom + dividerHeight).toFloat()
                c.drawRect(
                    left.toFloat() + leftPadding,
                    top,
                    right.toFloat() - rightPadding,
                    bottom,
                    p
                )
            }
        }
    }
}
