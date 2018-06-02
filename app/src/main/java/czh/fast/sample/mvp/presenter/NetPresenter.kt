package czh.fast.sample.mvp.presenter

import com.vise.log.ViseLog
import com.vise.xsnow.http.core.ApiTransformer.norTransformer
import com.vise.xsnow.http.mode.CacheMode
import czh.fast.lib.utils.transformer
import czh.fast.sample.api.apiservice
import czh.fast.sample.mvp.contract.NetContract

class NetPresenter(private val netView: NetContract.View) : NetContract.Presenter {

    init {
        netView.presenter = this
    }

    override fun normalTask() {
        netView.showLoading()
        apiservice.getAdvert().compose(norTransformer())
                .subscribe(
                        {
                            netView.showResult(it)
                            netView.hideLoading()
                        },
                        {
                            netView.hideLoading()
                            ViseLog.e(it.message)
                        }
                )


    }

    override fun cacheTask() {
        apiservice.getAdvert().compose(norTransformer())
                .compose(transformer(CacheMode.CACHE_AND_REMOTE))
                .subscribe(
                        {
                            netView.showResult(it.cacheData)
                            netView.hideLoading()
                        },
                        {
                            netView.hideLoading()
                            ViseLog.e(it.message)
                        }
                )

    }

}