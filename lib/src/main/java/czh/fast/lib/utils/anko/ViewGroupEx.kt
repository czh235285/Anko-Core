package czh.fast.lib.utils.anko

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import com.donkingliang.consecutivescroller.ConsecutiveScrollerLayout
import com.scwang.smartrefresh.layout.SmartRefreshLayout

open class _SmartRefreshLayout(ctx: Context): SmartRefreshLayout(ctx) {

    inline fun <T: View> T.lparams(
            c: Context?,
            attrs: AttributeSet?,
            init: SmartRefreshLayout.LayoutParams.() -> Unit
    ): T {
        val layoutParams = SmartRefreshLayout.LayoutParams(c!!, attrs!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T: View> T.lparams(
            c: Context?,
            attrs: AttributeSet?
    ): T {
        val layoutParams = SmartRefreshLayout.LayoutParams(c!!, attrs!!)
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T: View> T.lparams(
            width: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            height: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            init: SmartRefreshLayout.LayoutParams.() -> Unit
    ): T {
        val layoutParams = SmartRefreshLayout.LayoutParams(width, height)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T: View> T.lparams(
            width: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            height: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT
    ): T {
        val layoutParams = SmartRefreshLayout.LayoutParams(width, height)
        this@lparams.layoutParams = layoutParams
        return this
    }


    inline fun <T: View> T.lparams(
            source: ViewGroup.LayoutParams?,
            init: SmartRefreshLayout.LayoutParams.() -> Unit
    ): T {
        val layoutParams = SmartRefreshLayout.LayoutParams(source!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T: View> T.lparams(
            source: ViewGroup.LayoutParams?
    ): T {
        val layoutParams = SmartRefreshLayout.LayoutParams(source!!)
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T: View> T.lparams(
            source: ViewGroup.MarginLayoutParams?,
            init: SmartRefreshLayout.LayoutParams.() -> Unit
    ): T {
        val layoutParams = SmartRefreshLayout.LayoutParams(source!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T: View> T.lparams(
            source: ViewGroup.MarginLayoutParams?
    ): T {
        val layoutParams = SmartRefreshLayout.LayoutParams(source!!)
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T: View> T.lparams(
            source: SmartRefreshLayout.LayoutParams?,
            init: SmartRefreshLayout.LayoutParams.() -> Unit
    ): T {
        val layoutParams = SmartRefreshLayout.LayoutParams(source!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T: View> T.lparams(
            source: SmartRefreshLayout.LayoutParams?
    ): T {
        val layoutParams = SmartRefreshLayout.LayoutParams(source!!)
        this@lparams.layoutParams = layoutParams
        return this
    }
}


open class _ConsecutiveScrollerLayout(ctx: Context): ConsecutiveScrollerLayout(ctx) {

    inline fun <T: View> T.lparams(
            c: Context?,
            attrs: AttributeSet?,
            init: ConsecutiveScrollerLayout.LayoutParams.() -> Unit
    ): T {
        val layoutParams = ConsecutiveScrollerLayout.LayoutParams(c!!, attrs!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T: View> T.lparams(
            c: Context?,
            attrs: AttributeSet?
    ): T {
        val layoutParams = ConsecutiveScrollerLayout.LayoutParams(c!!, attrs!!)
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T: View> T.lparams(
            width: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            height: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            init: ConsecutiveScrollerLayout.LayoutParams.() -> Unit
    ): T {
        val layoutParams = ConsecutiveScrollerLayout.LayoutParams(width, height)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T: View> T.lparams(
            width: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            height: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT
    ): T {
        val layoutParams = ConsecutiveScrollerLayout.LayoutParams(width, height)
        this@lparams.layoutParams = layoutParams
        return this
    }


    inline fun <T: View> T.lparams(
            source: ViewGroup.LayoutParams?,
            init: ConsecutiveScrollerLayout.LayoutParams.() -> Unit
    ): T {
        val layoutParams = ConsecutiveScrollerLayout.LayoutParams(source!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T: View> T.lparams(
            source: ViewGroup.LayoutParams?
    ): T {
        val layoutParams = ConsecutiveScrollerLayout.LayoutParams(source!!)
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T: View> T.lparams(
            source: ViewGroup.MarginLayoutParams?,
            init: ConsecutiveScrollerLayout.LayoutParams.() -> Unit
    ): T {
        val layoutParams = ConsecutiveScrollerLayout.LayoutParams(source!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T: View> T.lparams(
            source: ViewGroup.MarginLayoutParams?
    ): T {
        val layoutParams = ConsecutiveScrollerLayout.LayoutParams(source!!)
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T: View> T.lparams(
            source: ConsecutiveScrollerLayout.LayoutParams?,
            init: ConsecutiveScrollerLayout.LayoutParams.() -> Unit
    ): T {
        val layoutParams = ConsecutiveScrollerLayout.LayoutParams(source!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T: View> T.lparams(
            source: ConsecutiveScrollerLayout.LayoutParams?
    ): T {
        val layoutParams = ConsecutiveScrollerLayout.LayoutParams(source!!)
        this@lparams.layoutParams = layoutParams
        return this
    }
}