package c.core.sample.base

import kotlinx.coroutines.*

class ScopeCallback(
    var block: suspend CoroutineScope.() -> Unit = {},
    var onError: (Throwable) -> Unit = {},
    var onFinally: () -> Unit = {}
)


fun CoroutineScope.safeLaunch(init: suspend ScopeCallback.() -> Unit): Job {
    return launch {
        val callback = ScopeCallback().apply { this.init() }
        try {
            coroutineScope {
                callback.block.invoke(this)
            }
        } catch (e: Throwable) {
            e.printStackTrace()
            callback.onError.invoke(e)
        } finally {
            callback.onFinally.invoke()
        }
    }
}