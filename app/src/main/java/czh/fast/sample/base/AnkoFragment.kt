package czh.fast.sample.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import czh.fast.lib.base.LoadingView
import czh.fast.lib.widget.LoadingDialog


//fragment基类
abstract class AnkoFragment : Fragment(), LoadingView {
    val act by lazy {
        requireActivity()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return layout()
    }


    var isLoadData = false


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (!isLazyLoad()) {
            afterInitView()
            isLoadData = true
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("当前Fragment", "==》 (${javaClass.simpleName}.kt:1)")
        if (!isLoadData) {
            afterInitView()
            isLoadData = true
        } else {
            refreshUi()
        }
    }

    //获取布局文件
    protected abstract fun layout(): View

    //初始化view
    protected abstract fun afterInitView()

    //重新回到当前fragment刷新Data
    open fun refreshUi() {}

    //是否需要懒加载
    open fun isLazyLoad(): Boolean {
        return true
    }


    private var mLoading: LoadingDialog? = null
    override fun showLoading() {
        if (mLoading == null) {
            mLoading = LoadingDialog(act)
        }
        mLoading?.show()
    }

    override fun hideLoading() {
        mLoading?.dismiss()
    }

}
