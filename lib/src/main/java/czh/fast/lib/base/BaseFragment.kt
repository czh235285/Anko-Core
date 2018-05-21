package czh.fast.lib.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.czh.library.LoadingDialog
import com.vise.xsnow.http.ViseHttp


//fragment基类
abstract class BaseFragment : Fragment(), View.OnClickListener {
    lateinit var mContext: Context
    protected var rootView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null)
            rootView = inflater.inflate(layoutResource, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("当前Fragment", "==》 (${javaClass.simpleName}.kt:1)")
        activity?.let {
            mContext = it
        }
        views?.forEach { it.setOnClickListener(this) }
        afterInitView()
    }

    //获取布局文件
    protected abstract val layoutResource: Int

    //初始化view
    protected abstract fun afterInitView()

    //点击事件view列表
    protected abstract val views: List<View>?

    override fun onDestroyView() {
        super.onDestroyView()
        ViseHttp.cancelAll()
        rootView = null
    }

    private var mLoading: LoadingDialog? = null
    fun showLoading() {
        if (mLoading == null) {
            mLoading = LoadingDialog(activity!!)
        }
        mLoading?.show()
    }

    fun stopLoading() {
        mLoading?.dismiss()
    }


}
