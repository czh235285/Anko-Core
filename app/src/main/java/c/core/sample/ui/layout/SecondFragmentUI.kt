package c.core.sample.ui.layout

import android.view.Gravity
import android.view.View
import c.core.ex.*
import c.core.sample.R
import c.core.sample.ui.activity.ListSampleActivity
import c.core.sample.ui.fragment.SecondFragment
import c.core.utils.color
import c.core.utils.openActivity
import c.core.utils.throttleClick
import org.jetbrains.anko.*

class SecondFragmentUI : AnkoComponent<SecondFragment> {
    override fun createView(ui: AnkoContext<SecondFragment>): View = with(ui) {
        verticalLayout {
            gravity = Gravity.CENTER
            text(24, "#262626", "textView扩展CompoundDrawables\n指定任意方向\n指定大小") {
                commonShape("#fafafa".color, 8.pxf)
                gravity = Gravity.CENTER
                padding = 20.px

//                leftDrawable(R.mipmap.ic_launcher,20.px,20.px)单独一个方向
                textDrawable {
                    top {
                        id = R.mipmap.ic_launcher
                        width = 20.px
                        height = 20.px
                        padding = 20.px
                    }
                    bottom {
                        id = R.mipmap.ic_launcher
                        width = 40.px
                        height = 40.px
                        padding = 20.px
                    }
                    right {
                        id = R.mipmap.ic_launcher
                        width = 60.px
                        height = 60.px
                        padding = 20.px
                    }
                }
            }.lparams(-1, wrapContent) {
                margin = 30.px
            }
        }
    }
}