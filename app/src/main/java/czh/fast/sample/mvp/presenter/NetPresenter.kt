package czh.fast.sample.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import czh.fast.sample.api.apiservice
import czh.fast.sample.base.BasePresenterImpl
import czh.fast.sample.base.safeLaunch
import czh.fast.sample.mvp.contract.NetContract
import kotlinx.coroutines.*

class NetPresenter(private val netView: NetContract.View) : BasePresenterImpl(), NetContract.Presenter {

    //并行多请求的例子
    override fun multipleRequestsTask() {
        presenterScope.safeLaunch {
            block = {
                val task1 = async {
                    apiservice.getBanner()
                }
                val task2 = async {
                    apiservice.getBanner()
                }

                //这里的await不能直接写前面，写前面就是串行请求了。
                val data1 = task1.await()
                val data2 = task2.await()
            }
        }
    }

    override fun normalTask() {
        presenterScope.safeLaunch {
            block = {
                val data = apiservice.getBanner()
                netView.showResult(data)
            }
        }
    }

}