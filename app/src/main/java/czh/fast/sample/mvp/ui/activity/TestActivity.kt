package czh.fast.sample.mvp.ui.activity

import czh.fast.lib.base.AnkoActivity
import czh.fast.sample.mvp.ui.layout.TestActivityUI
import org.jetbrains.anko.setContentView


class TestActivity : AnkoActivity() {
    val ui = TestActivityUI()
    override fun UI() {
        ui.setContentView(this)
    }

    override fun afterInitView() {

    }


}
