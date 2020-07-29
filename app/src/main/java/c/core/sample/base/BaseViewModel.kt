package c.core.sample.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel(), BaseLifecycleObserver {
    private var owner: LifecycleOwner? = null

    fun showLoading() {
        when (owner) {
            is AnkoActivity -> {
                (owner as? AnkoActivity)?.showLoading()
            }
            is AnkoFragment -> {
                (owner as? AnkoFragment)?.showLoading()
            }
            is BaseActivity<*> -> {
                (owner as? BaseActivity<*>)?.showLoading()
            }
            is BaseFragment<*> -> {
                (owner as? BaseFragment<*>)?.showLoading()
            }
        }
    }

    fun showLoadingView() {
        when (owner) {
            is AnkoActivity -> {
                (owner as? AnkoActivity)?.showLoadingView()
            }
            is AnkoFragment -> {
                (owner as? AnkoFragment)?.showLoadingView()
            }
            is BaseActivity<*> -> {
                (owner as? BaseActivity<*>)?.showLoadingView()
            }
            is BaseFragment<*> -> {
                (owner as? BaseFragment<*>)?.showLoadingView()
            }
        }
    }

    fun showLoadFailed() {
        when (owner) {
            is AnkoActivity -> {
                (owner as? AnkoActivity)?.showLoadFailed()
            }
            is AnkoFragment -> {
                (owner as? AnkoFragment)?.showLoadFailed()
            }

            is BaseActivity<*> -> {
                (owner as? BaseActivity<*>)?.showLoadFailed()
            }
            is BaseFragment<*> -> {
                (owner as? BaseFragment<*>)?.showLoadFailed()
            }
        }
    }

    fun showLoadSuccess() {
        when (owner) {
            is AnkoActivity -> {
                (owner as? AnkoActivity)?.showLoadSuccess()
            }
            is AnkoFragment -> {
                (owner as? AnkoFragment)?.showLoadSuccess()
            }

            is BaseActivity<*> -> {
                (owner as? BaseActivity<*>)?.showLoadSuccess()
            }
            is BaseFragment<*> -> {
                (owner as? BaseFragment<*>)?.showLoadSuccess()
            }
        }
    }

    fun hideLoading() {
        when (owner) {
            is AnkoActivity -> {
                (owner as? AnkoActivity)?.hideLoading()
            }
            is AnkoFragment -> {
                (owner as? AnkoFragment)?.hideLoading()
            }
            is BaseActivity<*> -> {
                (owner as? BaseActivity<*>)?.hideLoading()
            }
            is BaseFragment<*> -> {
                (owner as? BaseFragment<*>)?.hideLoading()
            }
        }
    }

    override fun onAny(owner: LifecycleOwner) {

    }

    override fun onCreate(owner: LifecycleOwner) {
        this.owner = owner
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
    }
}