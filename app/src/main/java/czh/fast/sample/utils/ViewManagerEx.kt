package czh.fast.sample.utils

import android.view.ViewManager
import com.youth.banner.Banner
import org.jetbrains.anko.custom.ankoView

inline fun ViewManager.banners(theme: Int = 0) = banners(theme) {}

inline fun ViewManager.banners(theme: Int = 0, init: Banner.() -> Unit): Banner {
    return ankoView({ Banner(it) }, theme, init)
}
