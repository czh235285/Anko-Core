package c.core.sample.ui.fragment

import android.view.View
import c.core.ex.anko.addArgs
import c.core.sample.base.AnkoFragment
import c.core.ex.anko.generateView
import c.core.sample.ui.layout.SampleFragmentUI


class SampleFragment private constructor() : AnkoFragment() {
    val ui = SampleFragmentUI()

    override fun ankoLayout(): View {
        return ui.generateView(this)
    }

    override fun afterInitView() {

    }

    companion object {

        fun newInstance(str: String): SampleFragment {
            return SampleFragment().addArgs("str" to str)
        }
    }
}
