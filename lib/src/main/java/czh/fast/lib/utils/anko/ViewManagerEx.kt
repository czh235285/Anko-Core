package czh.fast.lib.utils.anko

import android.content.Context
import android.support.v4.app.Fragment
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
 * circleImageView
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
 * circleImageView
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