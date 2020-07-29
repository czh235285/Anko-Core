package c.core.widget.viewpager

import android.content.Context
import android.view.animation.Interpolator
import android.widget.Scroller

/**
 * 重设Scroller的滑动速度
 */
class FixedSpeedScroller : Scroller {

    private var mDuration = 1000

    constructor(context: Context) : super(context) {}

    constructor(context: Context, interpolator: Interpolator) : super(context, interpolator) {}

    constructor(context: Context, interpolator: Interpolator, duration: Int) : this(context, interpolator) {
        mDuration = duration
    }

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
        super.startScroll(startX, startY, dx, dy, mDuration)
    }

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int) {
        super.startScroll(startX, startY, dx, dy, mDuration)
    }
}