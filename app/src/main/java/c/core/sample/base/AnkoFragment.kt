package c.core.sample.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import c.core.utils.getViewBinding
import c.core.widget.LoadingDialog
import c.core.widget.status.Gloading
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContextImpl


//fragment基类
abstract class AnkoFragment : Fragment() {
    val act by lazy {
        requireActivity()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ankoLayout()
    }


    private var isLoadData = false

    override fun onDestroyView() {
        super.onDestroyView()
        isLoadData = false
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (!isLazyLoad) {
            initLoadingStatusView()
            afterInitView()
            isLoadData = true
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("当前Fragment", "==》 (${javaClass.simpleName}.kt:1)")
        if (!isLoadData) {
            initLoadingStatusView()
            afterInitView()
            isLoadData = true
        } else {
            refreshUi()
        }
    }

    //获取布局文件
    protected abstract fun ankoLayout(): View

    //初始化view
    protected abstract fun afterInitView()

    //重新回到当前fragment刷新Data
    open fun refreshUi() {}

    //是否需要懒加载
    open val isLazyLoad: Boolean
        get() = true

    /** 被状态布局包裹的View **/
    protected open val wrappedView: View? = null

    private fun initLoadingStatusView() {
        if (wrappedView != null) {
            mHolder = mHolder ?: Gloading.getDefault().wrap(wrappedView).withRetry { onLoadRetry() }
        }
    }

    protected open fun onLoadRetry() {

    }

    private var mLoading: LoadingDialog? = null
    fun showLoading() {
        if (mLoading == null) {
            mLoading = LoadingDialog(act)
        }
        mLoading?.show()
    }

    fun hideLoading() {
        mLoading?.dismiss()
    }

    private var mHolder: Gloading.Holder? = null

    open fun showLoadingView() {
        mHolder?.showLoading()
    }

    open fun showLoadSuccess() {
        mHolder?.showLoadSuccess()
    }

    open fun showLoadFailed() {
        mHolder?.showLoadFailed()
    }

    open fun showEmpty() {
        mHolder?.showEmpty()
    }
}
