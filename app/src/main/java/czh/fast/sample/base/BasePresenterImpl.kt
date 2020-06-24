package czh.fast.sample.base

import androidx.lifecycle.*
import kotlinx.coroutines.*

abstract class BasePresenterImpl : BaseLifecycleObserver {

    lateinit var lifecycleScope: CoroutineScope

    override fun onAny(owner: LifecycleOwner) {

    }


    override fun onCreate(owner: LifecycleOwner) {
        lifecycleScope = owner.lifecycleScope
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
        //这里不需要取消了，lifecycleScope随生命周期结束会自己取消
        //lifecycleScope.cancel()
    }
}