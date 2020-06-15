package czh.fast.sample.base

import androidx.lifecycle.*
import kotlinx.coroutines.*

abstract class BasePresenterImpl : BaseLifecycleObserver {

    lateinit var presenterScope: CoroutineScope

    override fun onAny(owner: LifecycleOwner) {

    }

    override fun onCreate(owner: LifecycleOwner) {
        presenterScope = CoroutineScope(Dispatchers.Main + Job())
    }

    override fun onStart(owner: LifecycleOwner) {
    }

    override fun onStop(owner: LifecycleOwner) {
    }

    override fun onResume(owner: LifecycleOwner) {
    }

    override fun onPause(owner: LifecycleOwner) {
    }

    override fun onDestory(owner: LifecycleOwner) {
        presenterScope.cancel()
    }
}