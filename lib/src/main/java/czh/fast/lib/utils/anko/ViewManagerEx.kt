package czh.fast.lib.utils.anko

import android.view.ViewManager
import com.flyco.tablayout.CommonTabLayout
import com.rengwuxian.materialedittext.MaterialEditText
import czh.fast.lib.widget.NoScrollViewPager
import org.jetbrains.anko.custom.ankoView

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
