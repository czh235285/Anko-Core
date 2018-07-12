package czh.fast.sample.mvp.ui.layout

import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.youth.banner.Banner
import czh.fast.sample.R
import czh.fast.sample.mvp.ui.fragment.NetFragment
import czh.fast.sample.utils.banners
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class NetFragmentUI : AnkoComponent<NetFragment> {
    lateinit var banner: Banner
    override fun createView(ui: AnkoContext<NetFragment>): View = with(ui) {
        return verticalLayout {
            banner = banners().lparams(width = matchParent, height = 300)

            textView("普通请求"){
                onClick {
                    ui.owner.presenter.normalTask()
                }
            }.lparams(width = matchParent) {
                leftMargin = 30
                rightMargin = 30
                topMargin = 30

            }
            textView("缓存请求(断网有效)"){
                onClick {
                    ui.owner.presenter.cacheTask()
                }
            } .lparams(width = matchParent) {
                leftMargin = 30
                rightMargin = 30
                topMargin = 30

            }
        }.apply {
            applyRecursively {
                when (it) {
                    is TextView -> {
                        it.apply {
                            padding = 30
                            gravity = Gravity.CENTER
                            textColorResource = R.color.white
                            backgroundColorResource = R.color.colorPrimary
                        }
                    }
                }
            }
        }
    }
}