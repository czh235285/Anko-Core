package czh.fast.sample.mvp.contract

import czh.fast.lib.base.BasePresenter
import czh.fast.lib.base.BaseView

interface TestContract {
    interface Presenter : BasePresenter {

    }

    interface View : BaseView<Presenter> {

    }
}
