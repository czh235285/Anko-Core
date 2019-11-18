package czh.fast.sample.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import czh.fast.lib.base.LoadingView
import czh.fast.lib.widget.LoadingDialog


//fragment基类
abstract class AnkoFragment : Fragment(), LoadingView {
    var isInitView: Boolean = false

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        isInitView = true
        return layout()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("当前Fragment", "==》 (${javaClass.simpleName}.kt:1)")
        afterInitView()
    }

    //获取布局文件
    protected abstract fun layout(): View

    //初始化view
    protected abstract fun afterInitView()


    private var mLoading: LoadingDialog? = null
    override fun showLoading() {
        if (mLoading == null) {
            mLoading = LoadingDialog(activity!!)
        }
        mLoading?.show()
    }

    override fun hideLoading() {
        mLoading?.dismiss()
    }


}
