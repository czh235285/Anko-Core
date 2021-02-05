package c.core.sample.ui.layout

import android.view.Gravity
import android.view.View
import c.core.ex.commonShape
import c.core.ex.px
import c.core.ex.pxf
import c.core.ex.text
import c.core.sample.ui.activity.GLoadingActivity
import c.core.sample.ui.activity.ListSampleActivity
import c.core.sample.ui.fragment.FirstFragment
import c.core.utils.color
import c.core.utils.openActivity
import c.core.utils.throttleClick
import org.jetbrains.anko.*

class FirstFragmentUI : AnkoComponent<FirstFragment> {
    override fun createView(ui: AnkoContext<FirstFragment>): View = with(ui) {
        verticalLayout {
            text(24, "#262626") {
                throttleClick {
                    owner.openActivity<ListSampleActivity>()
                }
                commonShape("#fafafa".color, 8.pxf)
                gravity = Gravity.CENTER
                padding = 20.px
                setLineSpacing(1f,1.5f)
                text = buildString {
                    appendln("简单列表Demo")
                    appendln("继承AnkoListActivity")
                    append("几行代码实现分页加载,错误重试,预加载等等...")
                }
            }.lparams(-1, wrapContent) {
                margin = 30.px
            }
            text(24, "#262626", "状态管理demo") {
                throttleClick {
                    owner.openActivity<GLoadingActivity>()
                }
                gravity = Gravity.CENTER
                commonShape("#fafafa".color, 8.pxf)
            }.lparams(-1, 80.px) {
                margin = 30.px
            }
        }
    }
}
