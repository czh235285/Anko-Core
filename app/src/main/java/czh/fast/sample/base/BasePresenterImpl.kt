package czh.fast.sample.base

import com.vise.log.ViseLog
import kotlinx.coroutines.*

abstract class BasePresenterImpl  {

    val presenterScope: CoroutineScope by lazy {
        CoroutineScope(Dispatchers.Main + Job())
    }

    fun cancel (){
        presenterScope.cancel()
    }

    fun safeLaunch(
            action: suspend CoroutineScope.() -> Unit,
            onStart: () -> Unit = {},
            onError: (e: Throwable) -> Unit = {},
            onFinally: () -> Unit = {}
    ): Job {
        return presenterScope.launch {
            try {
                coroutineScope {
                    onStart.invoke()
                    action()
                }
            } catch (e: Throwable) {
                ViseLog.d(e)
                onError.invoke(e)
            } finally {
                onFinally.invoke()
            }
        }

    }
}