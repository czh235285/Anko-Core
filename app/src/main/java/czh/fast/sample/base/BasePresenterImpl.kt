package czh.fast.sample.base

import kotlinx.coroutines.*

abstract class BasePresenterImpl  {

    val presenterScope: CoroutineScope by lazy {
        CoroutineScope(Dispatchers.Main + Job())
    }

    fun cancel (){
        presenterScope.cancel()
    }
}