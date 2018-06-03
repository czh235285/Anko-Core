package czh.fast.sample.mvp.presenter

import czh.fast.sample.mvp.contract.TestContract

class TestPresenter(private val mView: TestContract.View) : TestContract.Presenter {
    init {
        mView.presenter = this
    }
}
