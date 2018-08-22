package czh.fast.sample.mvp.ui.activity

import org.jetbrains.anko.setContentView


import czh.fast.sample.base.AnkoActivity
import czh.fast.sample.mvp.ui.layout.activity.ImageActivityUI

class ImageActivity : AnkoActivity() {
    val ui = ImageActivityUI()
    override fun ankoLayout() {
        ui.setContentView(this)
    }

    override fun afterInitView() = with(ui) {


    }

}
