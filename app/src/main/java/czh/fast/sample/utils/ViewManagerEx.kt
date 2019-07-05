package czh.fast.sample.utils

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.ViewGroup
import android.view.ViewManager
import com.youth.banner.Banner
import czh.fast.sample.widget.AnkoToolBar
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView

inline fun ViewManager.banners(theme: Int = 0) = banners(theme) {}

inline fun ViewManager.banners(theme: Int = 0, init: Banner.() -> Unit): Banner {
    return ankoView({ Banner(it) }, theme, init)
}

inline fun ViewManager.ankoToolBar(theme: Int = 0, init: AnkoToolBar.() -> Unit): AnkoToolBar {
    return ankoView({
        AnkoToolBar(it).apply {
            layoutParams = ViewGroup.LayoutParams(matchParent, dip(48))
        }
    }, theme, init)
}

@SuppressLint("WrongConstant")
val unSelectedDrawable = GradientDrawable().apply {
    setColor(Color.parseColor("#FFFFFF"))
    gradientType = GradientDrawable.OVAL
    cornerRadius = 30f
}

@SuppressLint("WrongConstant")
val selectedDrawable = GradientDrawable().apply {
    setColor(Color.parseColor("#FF444A"))
    gradientType = GradientDrawable.OVAL
    cornerRadius = 30f
}