package czh.fast.lib.utils.anko

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewManager
import com.facebook.drawee.view.SimpleDraweeView
import com.flyco.tablayout.CommonTabLayout
import com.rengwuxian.materialedittext.MaterialEditText
import czh.fast.lib.widget.ItemLayout
import czh.fast.lib.widget.NoScrollViewPager
import czh.widget.ObservableRecylerView
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.support.v4.ctx
import com.facebook.drawee.generic.RoundingParams
import com.flyco.tablayout.SegmentTabLayout
import com.flyco.tablayout.SlidingTabLayout
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.zhy.view.flowlayout.TagFlowLayout
import czh.fast.lib.R
import czh.fast.lib.widget.viewpager.Mu5ViewPager
import czh.library.LikeView
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
 * simpleDraweeView
 */
inline fun ViewManager.simpleDraweeView(theme: Int = 0) = simpleDraweeView(theme) {}

/**
 * simpleDraweeView
 */
inline fun ViewManager.simpleDraweeView(theme: Int = 0, init: SimpleDraweeView.() -> Unit): SimpleDraweeView {
    return ankoView({ SimpleDraweeView(it) }, theme, init)
}


/**
 * circleImageView
 */
inline fun ViewManager.circleImageView() = circleImageView{
}

/**
 * circleImageView
 */
inline fun ViewManager.circleImageView(init: SimpleDraweeView.() -> Unit): SimpleDraweeView {
    return simpleDraweeView(0, init).apply {
        hierarchy.apply {
            val roundingParams = RoundingParams()
            roundingParams.roundAsCircle = true
            hierarchy.roundingParams = roundingParams
        }
    }
}



/**
 * roundImageView
 */
inline fun ViewManager.roundImageView(corners: Float) = roundImageView(corners) {
}

/**
 * roundImageView
 */
inline fun ViewManager.roundImageView(corners: Float, init: SimpleDraweeView.() -> Unit): SimpleDraweeView {
    return simpleDraweeView(0, init).apply {
        hierarchy.apply {
            val roundingParams = RoundingParams()
            roundingParams.setCornersRadius(corners)
            hierarchy.roundingParams = roundingParams
        }
    }
}


/**
 * roundImageView
 */
inline fun ViewManager.roundImageView(topLeft: Float, topRight: Float, bottomRight: Float, bottomLeft: Float) = roundImageView(topLeft, topRight, bottomRight, bottomLeft) {
}

/**
 * roundImageView
 */
inline fun ViewManager.roundImageView(topLeft: Float, topRight: Float, bottomRight: Float, bottomLeft: Float, init: SimpleDraweeView.() -> Unit): SimpleDraweeView {
    return simpleDraweeView(0, init).apply {
        hierarchy.apply {
            val roundingParams = RoundingParams()
            roundingParams.setCornersRadii(topLeft, topRight, bottomRight, bottomLeft)
            hierarchy.roundingParams = roundingParams
        }
    }
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

/**
 * LikeView
 */
inline fun ViewManager.likeView(theme: Int = 0) = likeView(theme) {}

/**
 * LikeView
 */
inline fun ViewManager.likeView(theme: Int = 0, init: LikeView.() -> Unit): LikeView {
    return ankoView({ LikeView(it) }, theme, init)
}