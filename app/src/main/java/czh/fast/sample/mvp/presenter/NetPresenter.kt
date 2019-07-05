package czh.fast.sample.mvp.presenter

import czh.fast.sample.api.apiservice
import czh.fast.sample.base.CoroutinesPresenter
import czh.fast.sample.mvp.contract.NetContract
import czh.fast.sample.utils.await
import kotlinx.coroutines.*
import java.lang.Exception

class NetPresenter(private val netView: NetContract.View) : CoroutinesPresenter(), NetContract.Presenter {

    //并行多请求的例子
    override fun multipleRequestsTask() {
        presenterScope.launch {
            try {
                //用到了async这里就需要包一层coroutineScope。否则内部抛异常会崩溃
                coroutineScope {

                    val task1 = async {
                        apiservice.getBanner().await()
                    }
                    val task2 = async {
                        apiservice.getBanner().await()
                    }

                    //这里的await不能直接写前面，写前面就是串行请求了。
                    val data1 = task1.await()
                    val data2 = task2.await()

                    //do something
                }

            } catch (e: Exception) {

            }
        }
    }

    override fun normalTask() {
        presenterScope.launch {
            try {
                val data = apiservice.getBanner().await()
                netView.showResult(data)
            } catch (e: Exception) {

            }
        }
    }

}