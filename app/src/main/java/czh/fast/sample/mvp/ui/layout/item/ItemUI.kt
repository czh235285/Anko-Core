package czh.fast.sample.mvp.ui.layout.item

import android.content.Context
import android.view.Gravity
import android.widget.TextView
import czh.fast.sample.utils.Text
import czh.fast.sample.utils.px
import org.jetbrains.anko.*

class ItemUI : AnkoComponent<Context> {

    lateinit var tv: TextView

    override fun createView(ui: AnkoContext<Context>) = with(ui) {
        verticalLayout {
            lparams(matchParent)
            tv = Text(30,"#262626") {
                gravity = Gravity.CENTER_VERTICAL
                leftPadding = 30.px
            }.lparams(matchParent, 90.px)
        }
    }
}