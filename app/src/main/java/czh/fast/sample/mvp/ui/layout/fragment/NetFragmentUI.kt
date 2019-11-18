package czh.fast.sample.mvp.ui.layout.fragment

import android.graphics.Color
import android.view.Gravity
import android.view.View
import com.youth.banner.Banner
import czh.fast.sample.R
import czh.fast.sample.mvp.ui.fragment.NetFragment
import czh.fast.sample.utils.*
import org.jetbrains.anko.*

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
            banner = banners().lparams(matchParent, 360.px)

            Text(26,"#ffffff","请求数据") {
                padding = 30
                gravity = Gravity.CENTER
                backgroundColorResource = R.color.colorPrimary
                setOnClickListener {
                    ui.owner.presenter.normalTask()
                }
            }.lparams(width = matchParent) {
                margin = 30.px
            }
        }
    }
}