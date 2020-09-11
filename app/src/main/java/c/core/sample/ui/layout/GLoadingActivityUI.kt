package c.core.sample.ui.layout

import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import c.core.ex.commonShape
import c.core.ex.px
import c.core.ex.pxf
import c.core.ex.text
import c.core.sample.ui.activity.GLoadingActivity
import c.core.utils.color
import c.core.utils.throttleClick
import c.core.widget.toolbar.ankoToolBar
import org.jetbrains.anko.*

class GLoadingActivityUI : AnkoComponent<GLoadingActivity> {
    lateinit var gloadView: View

    override fun createView(ui: AnkoContext<GLoadingActivity>) = with(ui) {
        verticalLayout {
            ankoToolBar {
                title = "状态管理"
            }
            linearLayout {
                orientation = LinearLayout.HORIZONTAL
                text(24, "#262626", "正常界面") {
                    throttleClick(1){}
                    throttleClick {
                        owner.showLoadSuccess()
                    }
                    commonShape("#fafafa".color, 8.pxf)
                    gravity = Gravity.CENTER
                }.lparams(0, -1, 1f) {
                    margin = 10.px
                }
                text(24, "#262626", "空布局") {
                    throttleClick {
                        owner.showEmpty()
                    }
                    gravity = Gravity.CENTER
                    commonShape("#fafafa".color, 8.pxf)
                }.lparams(0, -1, 1f) {
                    margin = 10.px
                }
                text(24, "#262626", "loading") {
                    throttleClick {
                        owner.showLoadingView()
                    }
                    commonShape("#fafafa".color, 8.pxf)
                    gravity = Gravity.CENTER
                }.lparams(0, -1, 1f) {
                    margin = 10.px
                }
                text(24, "#262626", "错误布局") {
                    throttleClick {
                        owner.showLoadFailed()
                    }
                    commonShape("#fafafa".color, 8.pxf)
                    gravity = Gravity.CENTER
                }.lparams(0, -1, 1f) {
                    margin = 10.px
                }


            }.lparams(-1, 80.px)

            gloadView = text(30, content = "正常布局") {
                gravity = Gravity.CENTER
            }.lparams(-1, -1)
        }
    }
}