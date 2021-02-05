package c.core.sample.base

import androidx.lifecycle.*
import kotlinx.coroutines.*

abstract class BaseLifecycleObserverImpl : BaseLifecycleObserver {

    lateinit var lifecycleScope: CoroutineScope

    override fun onAny(owner: LifecycleOwner) = Unit

    override fun onCreate(owner: LifecycleOwner) {
        lifecycleScope = owner.lifecycleScope
    }

    override fun onStart(owner: LifecycleOwner) = Unit

    override fun onStop(owner: LifecycleOwner) = Unit

    override fun onResume(owner: LifecycleOwner) = Unit

    override fun onPause(owner: LifecycleOwner) = Unit

    override fun onDestory(owner: LifecycleOwner) {
        // 这里不需要取消了，lifecycleScope随生命周期结束会自己取消
        // lifecycleScope.cancel()
    }
}
