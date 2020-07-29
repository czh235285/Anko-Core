package c.core.sample.ui.layout

import android.content.Context
import android.view.Gravity
import android.widget.TextView
import c.core.ex.commonShape
import c.core.ex.px
import c.core.ex.pxf
import c.core.ex.text
import c.core.sample.ui.activity.ListSampleActivity
import c.core.utils.color
import c.core.utils.openActivity
import c.core.utils.throttleClick
import org.jetbrains.anko.*

class ItemUI : AnkoComponent<Context> {
    lateinit var tv: TextView

    override fun createView(ui: AnkoContext<Context>) = with(ui) {
        frameLayout {
            lparams(-1)
            tv = text(24, "#262626") {
                commonShape("#fafafa".color, 8.pxf)
                gravity = Gravity.CENTER
                padding = 20.px
                setLineSpacing(1f, 1.5f)
            }.lparams(-1, wrapContent) {
                margin = 30.px
            }
        }
    }
}