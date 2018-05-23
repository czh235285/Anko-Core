package czh.fast.sample.mvp.presenter

import com.vise.xsnow.http.ViseHttp
import com.vise.xsnow.http.core.ApiTransformer
import com.vise.xsnow.http.mode.CacheMode
import czh.fast.sample.api.apiservice
import czh.fast.sample.mvp.contract.NetContract
import czh.fast.sample.mvp.model.Advert

class NetPresenter(private val netView: NetContract.View) : NetContract.Presenter {

    init {
        netView.presenter = this
    }

    override fun normalTask() {
        netView.showLoading()
        apiservice.getAdvert().compose(ApiTransformer.norTransformer())
                .subscribe {
                    netView.showResult(it)
                    netView.hideLoading()
                }

    }

    override fun cacheTask() {
        apiservice.getAdvert().compose(ApiTransformer.norTransformer())
                .compose(ViseHttp.getApiCache().transformer(CacheMode.CACHE_AND_REMOTE, Advert::class.java))
                .subscribe {
                    netView.showResult(it.cacheData)
                }
    }

}