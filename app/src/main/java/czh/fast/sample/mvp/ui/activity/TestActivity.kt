package czh.fast.sample.mvp.ui.activity

import android.view.View
import czh.fast.sample.R
import czh.fast.sample.mvp.contract.TestContract
import czh.fast.sample.mvp.presenter.TestPresenter
import kotlinx.android.synthetic.main.activity_test.*


import czh.fast.lib.base.BaseActivity

class TestActivity : BaseActivity(), TestContract.View {
    override val layoutId: Int = R.layout.activity_test

    override val views: List<View>? get() = null
    override var presenter: TestContract.Presenter = TestPresenter(this)
    override fun afterInitView() {

    }

    override fun onClick(v: View) {
        when (v) {

        }
    }
}
