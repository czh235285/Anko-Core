package czh.fast.lib.utils.anko

import android.content.Context
import android.support.v4.app.Fragment
import android.view.View
import android.view.ViewManager
import com.flyco.tablayout.CommonTabLayout
import com.rengwuxian.materialedittext.MaterialEditText
import czh.fast.lib.widget.ItemLayout
import czh.fast.lib.widget.NoScrollViewPager
import czh.widget.ObservableRecylerView
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.support.v4.ctx

fun <T : Fragment> AnkoComponent<T>.setContentView(activity: T): View = createView(AnkoContext.create(activity.ctx, activity))

fun AnkoComponent<Context>.setContentView(context: Context): View = createView(AnkoContext.create(context))

/**
 * tabLayout
 */
inline fun ViewManager.commonTabLayout(theme: Int = 0) = commonTabLayout(theme) {}

/**
 * tabLayout
 */
inline fun ViewManager.commonTabLayout(theme: Int = 0, init: CommonTabLayout.() -> Unit): CommonTabLayout {
    return ankoView({ CommonTabLayout(it) }, theme, init)
}

/**
 * 禁止滑动的Viewpager
 */
inline fun ViewManager.noScrollViewPager(theme: Int = 0) = noScrollViewPager(theme) {}

/**
 * 禁止滑动的Viewpager
 */
inline fun ViewManager.noScrollViewPager(theme: Int = 0, init: NoScrollViewPager.() -> Unit): NoScrollViewPager {
    return ankoView({ NoScrollViewPager(it) }, theme, init)
}

/**
 * MaterialEditText
 */
inline fun ViewManager.materialEditText(theme: Int = 0) = materialEditText(theme) {}

/**
 * MaterialEditText
 */
inline fun ViewManager.materialEditText(theme: Int = 0, init: MaterialEditText.() -> Unit): MaterialEditText {
    return ankoView({ MaterialEditText(it) }, theme, init)
}


/**
 * 可监听上滑下滑的RecylerView
 */
inline fun ViewManager.observableRecylerView(theme: Int = 0) = observableRecylerView(theme) {}

/**
 * 可监听上滑下滑的RecylerView
 */
inline fun ViewManager.observableRecylerView(theme: Int = 0, init: ObservableRecylerView.() -> Unit): ObservableRecylerView {
    return ankoView({ ObservableRecylerView(it) }, theme, init)
}

/**
 * 设置页面通用itemlayout
 */
inline fun ViewManager.itemLayout(theme: Int = 0) = itemLayout(theme) {}

/**
 * 设置页面通用itemlayout
 */
inline fun ViewManager.itemLayout(theme: Int = 0, init: ItemLayout.() -> Unit): ItemLayout {
    return ankoView({ ItemLayout(it) }, theme, init)
}