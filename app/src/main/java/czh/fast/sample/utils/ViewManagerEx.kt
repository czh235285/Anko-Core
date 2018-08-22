package czh.fast.sample.utils

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

///**
// * 设置页面通用itemlayout
// */
//inline fun ViewManager.toolbars(title: String, crossinline ivClick: () -> Unit): View {
//
//    var tv: TextView
//    return relativeLayout {
//        backgroundColor = Color.parseColor("#ffffff")
//        lparams(matchParent, dip(48))
//        imageView(R.mipmap.btn_back) {
//            setPadding(30, 30, 30, 30)
//            scaleType = ImageView.ScaleType.CENTER
//            setOnClickListener {
//                ivClick.invoke()
//            }
//        }.lparams(dip(48), dip(48))
//        tv = textView(title) {
//
//            textColor = Color.parseColor("#333333")
//            textSize = 20f
//        }.lparams { centerInParent() }
//    }.apply {
//    }
//}

inline fun ViewManager.ankoToolBar(theme: Int = 0, init: AnkoToolBar.() -> Unit): AnkoToolBar {
    return ankoView({
        AnkoToolBar(it).apply {
            layoutParams = ViewGroup.LayoutParams(matchParent, dip(48))
        }
    }, theme, init)
}