package czh.fast.lib.widget


import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Dell on 2017/10/27.
 */

class SimpleDividerDecoration(color: Int, private val dividerHeight: Int) : RecyclerView.ItemDecoration() {
   var dividerPaint : Paint

    init {
        dividerPaint = Paint()
        dividerPaint.color = color
    }


    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = dividerHeight
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State?) {
        val childCount = parent.childCount
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        for (i in 0..childCount - 1 - 1) {
            val view = parent.getChildAt(i)
            val top = view.bottom.toFloat()
            val bottom = (view.bottom + dividerHeight).toFloat()
            c.drawRect(left.toFloat(), top, right.toFloat(), bottom, dividerPaint)
        }
    }
}

