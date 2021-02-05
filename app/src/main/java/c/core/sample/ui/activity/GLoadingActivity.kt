package c.core.sample.ui.activity

import android.view.View
import c.core.sample.base.AnkoActivity
import c.core.sample.ui.layout.GLoadingActivityUI
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.toast

class GLoadingActivity : AnkoActivity() {
    val ui = GLoadingActivityUI()
    override fun ankoLayout() {
        ui.setContentView(this)
    }

    override val wrappedView: View?
        get() = ui.gloadView

    override fun onLoadRetry() {
        toast("点击重试")
    }

    override fun afterInitView() = Unit
}
