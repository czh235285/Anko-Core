package c.core.sample.ui.layout

import android.view.Gravity
import android.view.View
import android.widget.TextView
import c.core.ex.hpx
import c.core.ex.px
import c.core.ex.statusBarHeight
import c.core.ex.text
import c.core.sample.ui.fragment.ThirdFragment
import c.core.utils.color
import com.leaf.library.StatusBarUtil
import org.jetbrains.anko.*

class ThirdFragmentUI : AnkoComponent<ThirdFragment> {

    override fun createView(ui: AnkoContext<ThirdFragment>): View = with(ui) {
        frameLayout {
            val viewpagerHeight = 1334.hpx - 1 - 133.px - statusBarHeight
            text(32, "#ffffff") {
                text = "比xml更简单的屏幕适配"
                backgroundColor = "#ff0000".color
                gravity = Gravity.CENTER
            }.lparams(250.px, viewpagerHeight / 3)
            text(32, "#ffffff") {
                text = "父布局是frameLayout"
                gravity = Gravity.CENTER
                backgroundColor = "#00ff00".color
            }.lparams(250.px, viewpagerHeight / 3) {
                leftMargin = 250.px
                topMargin = viewpagerHeight / 3
            }
            text(32, "#ffffff") {
                text = "不用权重直接实现屏幕均分"
                gravity = Gravity.CENTER
                backgroundColor = "#ff0000".color
            }.lparams(250.px, viewpagerHeight / 3) {
                leftMargin = 500.px
                topMargin = viewpagerHeight * 2 / 3
            }

        }
    }
}