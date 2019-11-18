package czh.fast.sample.mvp.ui.activity

import czh.fast.sample.base.AnkoActivity
import czh.fast.sample.mvp.ui.layout.AdaptationActivityUI
import org.jetbrains.anko.setContentView


class AdaptationActivity : AnkoActivity() {
    val ui = AdaptationActivityUI()

    override fun ankoLayout() {
        ui.setContentView(this)
    }

    override fun afterInitView() {

    }

}