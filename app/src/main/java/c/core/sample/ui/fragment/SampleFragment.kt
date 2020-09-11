package c.core.sample.ui.fragment

import android.view.View
import androidx.fragment.app.activityViewModels
import c.core.ex.anko.addArgs
import c.core.sample.base.AnkoFragment
import c.core.ex.anko.generateView
import c.core.sample.ui.layout.SampleFragmentUI
import c.core.sample.ui.viewmodel.MagicIndicatorViewModel


class SampleFragment private constructor() : AnkoFragment() {
    val viewModel by activityViewModels<MagicIndicatorViewModel>()

    val ui = SampleFragmentUI()

    override fun ankoLayout(): View {
        return ui.generateView(this)
    }

    override fun afterInitView() {

    }

    companion object {

        fun newInstance(position: Int): SampleFragment {
            return SampleFragment().addArgs("position" to position)
        }
    }
}
