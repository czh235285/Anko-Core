package czh.fast.lib.utils.anko

import android.content.Context
import android.graphics.Color
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewManager
import com.facebook.drawee.view.SimpleDraweeView
import com.rengwuxian.materialedittext.MaterialEditText
import czh.fast.lib.widget.ItemLayout
import czh.fast.lib.widget.NoScrollViewPager
import czh.widget.ObservableRecylerView
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.support.v4.ctx
import com.facebook.drawee.generic.RoundingParams
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.zhy.view.flowlayout.TagFlowLayout
import czh.fast.lib.R
import czh.fast.lib.widget.tablayout.CommonTabLayout
import czh.fast.lib.widget.tablayout.SegmentTabLayout
import czh.fast.lib.widget.tablayout.SlidingTabLayout
import czh.fast.lib.widget.viewpager.Mu5ViewPager
import org.jetbrains.anko.*


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
 * tabLayout
 */
inline fun ViewManager.commonTabLayout(textSelectColor: String = "#3F51B5", textUnSelectColor: String = "#666666", textsize: Float = 12f, iconHeight: Float = 23f, iconWidth: Float = 23f) = commonTabLayout(textSelectColor, textUnSelectColor, textsize, iconHeight, iconWidth) { }

/**
 * tabLayout
 */
inline fun ViewManager.commonTabLayout(textSelectColor: String = "#3F51B5", textUnSelectColor: String = "#666666", textsize: Float = 12f, iconHeight: Float = 23f, iconWidth: Float = 23f, init: CommonTabLayout.() -> Unit): CommonTabLayout {

    return ankoView({
        CommonTabLayout(it).apply {
            this.textSelectColor = Color.parseColor(textSelectColor)
            this.textUnselectColor = Color.parseColor(textUnSelectColor)
            this.textsize = textsize
            this.iconHeight = iconHeight
            this.iconWidth = iconWidth
        }
    }, 0, init)
}

/**
 * tabLayout
 */
inline fun ViewManager.slidingTabLayout(theme: Int = 0) = slidingTabLayout(theme) {}

/**
 * tabLayout
 */
inline fun ViewManager.slidingTabLayout(theme: Int = 0, init: SlidingTabLayout.() -> Unit): SlidingTabLayout {
    return ankoView({ SlidingTabLayout(it) }, theme, init)
}

/**
 * tabLayout
 */
inline fun ViewManager.segmentTabLayout(theme: Int = 0) = segmentTabLayout(theme) {}

/**
 * tabLayout
 */
inline fun ViewManager.segmentTabLayout(theme: Int = 0, init: SegmentTabLayout.() -> Unit): SegmentTabLayout {
    return ankoView({ SegmentTabLayout(it) }, theme, init)
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

/**
 * mu5ViewPager
 */
inline fun ViewManager.mu5ViewPager(theme: Int = 0) = mu5ViewPager(theme) {}

/**
 * mu5ViewPager
 */
inline fun ViewManager.mu5ViewPager(theme: Int = 0, init: Mu5ViewPager.() -> Unit): Mu5ViewPager {
    return ankoView({ Mu5ViewPager(it) }, theme, init)
}

/**
 * smartRefreshLayout
 */
inline fun ViewManager.smartRefreshLayout(theme: Int = 0) = smartRefreshLayout(theme) {
}

/**
 * smartRefreshLayout
 */
inline fun ViewManager.smartRefreshLayout(theme: Int = 0, init: SmartRefreshLayout.() -> Unit): SmartRefreshLayout {
    return ankoView({ SmartRefreshLayout(it) }, theme, init)
}




/**
 * recyclerView
 */
inline fun ViewManager.recyclerView(theme: Int = 0) = recyclerView(theme) {
}

/**
 * recyclerView
 */
inline fun ViewManager.recyclerView(theme: Int = 0, init: RecyclerView.() -> Unit): RecyclerView {
    return ankoView({ RecyclerView(it) }, theme, init)
}

/**
 * tagFlowLayout
 */
inline fun ViewManager.tagFlowLayout(theme: Int = 0) = tagFlowLayout(theme) {}

/**
 * tagFlowLayout
 */
inline fun ViewManager.tagFlowLayout(theme: Int = 0, init: TagFlowLayout.() -> Unit): TagFlowLayout {
    return ankoView({ TagFlowLayout(it) }, theme, init)
}

