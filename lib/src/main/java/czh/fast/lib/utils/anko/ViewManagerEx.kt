package czh.fast.lib.utils.anko

import android.content.Context
import android.support.v4.app.Fragment
import android.view.View
import android.view.ViewManager
import com.flyco.tablayout.CommonTabLayout
import com.rengwuxian.materialedittext.MaterialEditText
import czh.fast.lib.widget.NoScrollViewPager
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.support.v4.ctx

fun <T : Fragment> AnkoComponent<T>.setContentView(activity: T): View = createView(AnkoContext.create(activity.ctx, activity))

fun AnkoComponent<Context>.setContentView(context: Context): View = createView(AnkoContext.create(context))


inline fun ViewManager.commonTabLayout(theme: Int = 0) = commonTabLayout(theme) {}

inline fun ViewManager.commonTabLayout(theme: Int = 0, init: CommonTabLayout.() -> Unit): CommonTabLayout {
    return ankoView({ CommonTabLayout(it) }, theme, init)
}

inline fun ViewManager.noScrollViewPager(theme: Int = 0) = noScrollViewPager(theme) {}

inline fun ViewManager.noScrollViewPager(theme: Int = 0, init: NoScrollViewPager.() -> Unit): NoScrollViewPager {
    return ankoView({ NoScrollViewPager(it) }, theme, init)
}

inline fun ViewManager.materialEditText(theme: Int = 0) = materialEditText(theme) {}

inline fun ViewManager.materialEditText(theme: Int = 0, init: MaterialEditText.() -> Unit): MaterialEditText {
    return ankoView({ MaterialEditText(it) }, theme, init)
}
