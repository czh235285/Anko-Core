package czh.fast.lib.widget


import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created  on 2018/5/20.
 * @author czh
 */

class SimpleDividerDecoration(color: Int, private val dividerHeight: Int) : RecyclerView.ItemDecoration() {
    private var dividerPaint: Paint = Paint()

    init {
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

        (0 until childCount - 1).forEach {
            val view = parent.getChildAt(it)
            val top = view.bottom.toFloat()
            val bottom = (view.bottom + dividerHeight).toFloat()
            c.drawRect(left.toFloat(), top, right.toFloat(), bottom, dividerPaint)
        }
    }
}

