package c.core.ex.anko

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewManager
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import c.core.utils.changeAlpha
import c.core.utils.color
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import org.jetbrains.anko.AnkoViewDslMarker
import org.jetbrains.anko._LinearLayout
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.linearLayout

open class _SmartRefreshLayout(ctx: Context) : SmartRefreshLayout(ctx) {

    inline fun <T : View> T.lparams(
        c: Context?,
        attrs: AttributeSet?,
        init: LayoutParams.() -> Unit
    ): T {
        val layoutParams = LayoutParams(c!!, attrs!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T : View> T.lparams(
        c: Context?,
        attrs: AttributeSet?
    ): T {
        val layoutParams = LayoutParams(c!!, attrs!!)
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T : View> T.lparams(
        width: Int = -2,
        height: Int = -2,
        init: LayoutParams.() -> Unit
    ): T {
        val layoutParams = LayoutParams(width, height)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T : View> T.lparams(
        width: Int = -2,
        height: Int = -2
    ): T {
        val layoutParams = LayoutParams(width, height)
        this@lparams.layoutParams = layoutParams
        return this
    }
}

/**
 * cardView
 */
fun ViewManager.cardView(theme: Int = 0) = cardView(theme) {
}

/**
 * cardView
 */
inline fun ViewManager.cardView(
    theme: Int = 0,
    init: _CardView.() -> Unit
): CardView {
    return ankoView({ _CardView(it) }, theme, init)
}

open class _CardView(ctx: Context) : CardView(ctx) {

    inline fun <T : View> T.lparams(
        c: Context?,
        attrs: AttributeSet?,
        init: LayoutParams.() -> Unit
    ): T {
        val layoutParams = LayoutParams(c!!, attrs!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T : View> T.lparams(
        c: Context?,
        attrs: AttributeSet?
    ): T {
        val layoutParams = LayoutParams(c!!, attrs!!)
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T : View> T.lparams(
        width: Int = -2,
        height: Int = -2,
        init: LayoutParams.() -> Unit
    ): T {
        val layoutParams = LayoutParams(width, height)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T : View> T.lparams(
        width: Int = -2,
        height: Int = -2
    ): T {
        val layoutParams = LayoutParams(width, height)
        this@lparams.layoutParams = layoutParams
        return this
    }
}

/**
 * swipeRefreshLayout
 */
fun ViewManager.swipeRefreshLayout(theme: Int = 0) = swipeRefreshLayout(theme) {
}

/**
 * swipeRefreshLayout
 */
inline fun ViewManager.swipeRefreshLayout(
    theme: Int = 0,
    init: _SwipeRefreshLayout.() -> Unit
): SwipeRefreshLayout {
    return ankoView({
        _SwipeRefreshLayout(it).apply {
            setColorSchemeColors("ff8686".color, "ff8686".color.changeAlpha(0.5f))
        }
    }, theme, init)
}

open class _SwipeRefreshLayout(ctx: Context) : SwipeRefreshLayout(ctx) {

    inline fun <T : View> T.lparams(
        width: Int = -2,
        height: Int = -2,
        init: LayoutParams.() -> Unit
    ): T {
        val layoutParams = LayoutParams(width, height)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T : View> T.lparams(
        width: Int = -2,
        height: Int = -2
    ): T {
        val layoutParams = LayoutParams(width, height)
        this@lparams.layoutParams = layoutParams
        return this
    }
}

/**
 * constraintLayout
 */
fun ViewManager.constraintLayout(theme: Int = 0) = constraintLayout(theme) {
}

/**
 * constraintLayout
 */
inline fun ViewManager.constraintLayout(
    theme: Int = 0,
    init: _ConstraintLayout.() -> Unit
): ConstraintLayout {
    return ankoView({ _ConstraintLayout(it) }, theme, init)
}

open class _ConstraintLayout(ctx: Context) : ConstraintLayout(ctx) {

    inline fun <T : View> T.lparams(
        width: Int = -2,
        height: Int = -2,
        init: LayoutParams.() -> Unit
    ): T {
        val layoutParams = LayoutParams(width, height)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T : View> T.lparams(
        width: Int = -2,
        height: Int = -2
    ): T {
        val layoutParams = LayoutParams(width, height)
        this@lparams.layoutParams = layoutParams
        return this
    }
}

/**
 * coordinatorLayout
 */
fun ViewManager.coordinatorLayout(theme: Int = 0) = coordinatorLayout(theme) {
}

/**
 * coordinatorLayout
 */
inline fun ViewManager.coordinatorLayout(
    theme: Int = 0,
    init: _CoordinatorLayout.() -> Unit
): CoordinatorLayout {
    return ankoView({ _CoordinatorLayout(it) }, theme, init)
}

open class _CoordinatorLayout(ctx: Context) : CoordinatorLayout(ctx) {

    inline fun <T : View> T.lparams(
        width: Int = -2,
        height: Int = -2,
        init: LayoutParams.() -> Unit
    ): T {
        val layoutParams = LayoutParams(width, height)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T : View> T.lparams(
        width: Int = -2,
        height: Int = -2
    ): T {
        val layoutParams = LayoutParams(width, height)
        this@lparams.layoutParams = layoutParams
        return this
    }
}

/**
 * appBarLayout
 */
fun ViewManager.appBarLayout(theme: Int = 0) = appBarLayout(theme) {
}

/**
 * appBarLayout
 */
inline fun ViewManager.appBarLayout(
    theme: Int = 0,
    init: _AppBarLayout.() -> Unit
): AppBarLayout {
    return ankoView({ _AppBarLayout(it) }, theme, init)
}

open class _AppBarLayout(ctx: Context, theme: Int = 0) : AppBarLayout(ctx) {

    inline fun <T : View> T.lparams(
        width: Int = -2,
        height: Int = -2,
        init: LayoutParams.() -> Unit
    ): T {
        val layoutParams = LayoutParams(width, height)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T : View> T.lparams(
        width: Int = -2,
        height: Int = -2
    ): T {
        val layoutParams = LayoutParams(width, height)
        this@lparams.layoutParams = layoutParams
        return this
    }
}

/**
 * collapsingToolbarLayout
 */
fun ViewManager.collapsingToolbarLayout(theme: Int = 0) = collapsingToolbarLayout(theme) {
}

/**
 * collapsingToolbarLayout
 */
inline fun ViewManager.collapsingToolbarLayout(
    theme: Int = 0,
    init: _CollapsingToolbarLayout.() -> Unit
): CollapsingToolbarLayout {
    return ankoView({ _CollapsingToolbarLayout(it) }, theme, init)
}

open class _CollapsingToolbarLayout(ctx: Context) : CollapsingToolbarLayout(ctx) {
    inline fun <T : View> T.lparams(
        width: Int = -2,
        height: Int = -2,
        init: LayoutParams.() -> Unit
    ): T {
        val layoutParams = LayoutParams(width, height)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T : View> T.lparams(
        width: Int = -2,
        height: Int = -2
    ): T {
        val layoutParams = LayoutParams(width, height)
        this@lparams.layoutParams = layoutParams
        return this
    }
}

/**
 * textInputEditText
 */
fun ViewManager.textInputEditText(theme: Int = 0) = textInputEditText(theme) {
}

/**
 * textInputEditText
 */
inline fun ViewManager.textInputEditText(
    theme: Int = 0,
    init: TextInputEditText.() -> Unit
): TextInputEditText {
    return ankoView({ TextInputEditText(it) }, theme, init)
}

/**
 * textInputLayout
 */
fun ViewManager.textInputLayout(theme: Int = 0) = textInputLayout(theme) {
}

/**
 * textInputLayout
 */
inline fun ViewManager.textInputLayout(
    theme: Int = 0,
    init: _TextInputLayout.() -> Unit
): TextInputLayout {
    return ankoView({ _TextInputLayout(it) }, theme, init)
}

open class _TextInputLayout(ctx: Context) : TextInputLayout(ctx) {

    inline fun <T : View> T.lparams(
        width: Int = -2,
        height: Int = -2,
        init: LayoutParams.() -> Unit
    ): T {
        val layoutParams = LayoutParams(width, height)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T : View> T.lparams(
        width: Int = -2,
        height: Int = -2
    ): T {
        val layoutParams = LayoutParams(width, height)
        this@lparams.layoutParams = layoutParams
        return this
    }
}

fun ViewManager.horizontalLayout(init: (@AnkoViewDslMarker _LinearLayout).() -> Unit): LinearLayout =
    linearLayout {
        orientation = LinearLayout.HORIZONTAL
        init()
    }
