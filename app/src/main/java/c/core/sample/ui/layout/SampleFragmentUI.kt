package c.core.sample.ui.layout

import android.graphics.Color
import android.view.Gravity
import android.view.View
import c.core.ex.text
import c.core.sample.ui.fragment.SampleFragment
import org.jetbrains.anko.*

class SampleFragmentUI : AnkoComponent<SampleFragment> {


    override fun createView(ui: AnkoContext<SampleFragment>): View = with(ui) {
        frameLayout {
            backgroundColor = Color.WHITE
            text(36, content = owner.arguments?.getString("str")) {
            }.lparams {
                gravity = Gravity.CENTER
            }

        }
    }
}