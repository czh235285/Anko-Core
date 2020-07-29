package c.core.sample.ui.layout

import android.view.Gravity
import android.view.View
import android.widget.TextView
import c.core.ex.text
import c.core.sample.ui.fragment.SampleFragment
import org.jetbrains.anko.*

class SampleFragmentUI : AnkoComponent<SampleFragment> {

    lateinit var tv: TextView

    override fun createView(ui: AnkoContext<SampleFragment>): View = with(ui) {
        frameLayout {
            tv = text(36, "#333333") {

            }.lparams {
                gravity = Gravity.CENTER
            }
        }
    }
}