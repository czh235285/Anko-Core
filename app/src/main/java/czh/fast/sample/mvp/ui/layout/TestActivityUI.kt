package czh.fast.sample.mvp.ui.layout

import android.view.View
import czh.fast.sample.mvp.ui.activity.TestActivity
import org.jetbrains.anko.*

class TestActivityUI : AnkoComponent<TestActivity> {
    override fun createView(ui: AnkoContext<TestActivity>): View= with(ui) {
        return verticalLayout {

        }
    }

}