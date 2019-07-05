package czh.fast.sample.mvp.ui.layout.fragment

import android.graphics.Color
import android.view.Gravity
import android.view.View
import com.youth.banner.Banner
import czh.fast.sample.R
import czh.fast.sample.mvp.ui.fragment.NetFragment
import czh.fast.sample.utils.ankoToolBar
import czh.fast.sample.utils.banners
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class NetFragmentUI : AnkoComponent<NetFragment> {
    lateinit var banner: Banner
    override fun createView(ui: AnkoContext<NetFragment>): View = with(ui) {
        return verticalLayout {
            ankoToolBar {
                title = "网络"
                titleColor = Color.parseColor("#ffffff")
                backgroundColorResource = R.color.colorPrimaryDark
                hideNavigation()
            }
            banner = banners().lparams(width = matchParent, height = dip(175))

            textView("请求数据") {
                padding = 30
                gravity = Gravity.CENTER
                textColorResource = R.color.white
                backgroundColorResource = R.color.colorPrimary
                setOnClickListener {

                    ui.owner.presenter.normalTask()
                }
            }.lparams(width = matchParent) {
                leftMargin = 30
                rightMargin = 30
                topMargin = 30

            }
        }
    }
}