package czh.fast.sample.mvp.contract

import czh.fast.lib.base.BasePresenter
import czh.fast.lib.base.BaseView
import czh.fast.sample.mvp.model.Banner

interface NetContract {
    interface Presenter : BasePresenter {
        fun normalTask()
        fun multipleRequestsTask()
    }

    interface View : BaseView {
       fun showResult(banner: Banner)
    }
}