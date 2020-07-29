package c.core.ex.anko

import android.view.View
import android.view.ViewManager
import androidx.viewpager.widget.ViewPager
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import c.core.widget.NoScrollViewPager
import c.core.widget.viewpager.Mu5ViewPager
import org.jetbrains.anko.custom.ankoView


/**
 * 禁止滑动的Viewpager
 */
fun ViewManager.noScrollViewPager(theme: Int = 0) = noScrollViewPager(theme) {}

/**
 * 禁止滑动的Viewpager
 */
inline fun ViewManager.noScrollViewPager(
    theme: Int = 0,
    init: NoScrollViewPager.() -> Unit
): NoScrollViewPager {
    return ankoView({
        NoScrollViewPager(it).apply {
            overScrollMode = View.OVER_SCROLL_NEVER
        }
    }, theme, init)
}

/**
 * Viewpager
 */
fun ViewManager.viewPager(theme: Int = 0) = viewPager(theme) {}

/**
 * Viewpager
 */
inline fun ViewManager.viewPager(
    theme: Int = 0,
    init: ViewPager.() -> Unit
): ViewPager {
    return ankoView({
        ViewPager(it).apply {
            overScrollMode = View.OVER_SCROLL_NEVER
        }
    }, theme, init)
}


/**
 * mu5ViewPager
 */
fun ViewManager.mu5ViewPager(theme: Int = 0) = mu5ViewPager(theme) {}

/**
 * mu5ViewPager
 */
inline fun ViewManager.mu5ViewPager(theme: Int = 0, init: Mu5ViewPager.() -> Unit): Mu5ViewPager {
    return ankoView({ Mu5ViewPager(it) }, theme, init)
}

/**
 * smartRefreshLayout
 */
fun ViewManager.smartRefreshLayout(theme: Int = 0) = smartRefreshLayout(theme) {

}

/**
 * smartRefreshLayout
 */
inline fun ViewManager.smartRefreshLayout(
    theme: Int = 0,
    init: _SmartRefreshLayout.() -> Unit
): SmartRefreshLayout {
    return ankoView({ _SmartRefreshLayout(it) }, theme, init)
}


