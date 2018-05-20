package czh.fast.sample.base

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.czh.library.LoadingDialog
import com.vise.xsnow.http.ViseHttp
import czh.fast.sample.api.ApiService


//fragment基类
abstract class BaseFragment : Fragment() {
    var apiservice = ViseHttp.RETROFIT<Any>().create<ApiService>(ApiService::class.java)

    protected var rootView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null)
            rootView = inflater.inflate(layoutResource, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("当前Fragment", "==》 (${javaClass.simpleName}.kt:1)")
        afterInitView()
    }

    //获取布局文件
    protected abstract val layoutResource: Int

    //初始化view
    protected abstract fun afterInitView()


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
