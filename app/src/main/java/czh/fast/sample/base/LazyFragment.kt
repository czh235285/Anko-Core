package czh.fast.sample.base

/**
 * Created by Dell on 2017/11/22.
 */

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.czh.library.LoadingDialog
import com.vise.xsnow.http.ViseHttp
import czh.fast.lib.base.LoadingView

//fragment基类
abstract class LazyFragment : Fragment() , View.OnClickListener, LoadingView {
    lateinit var mContext: Context
    private var rootView: View? = null
    var isViewCreated = false
    var isLoadData = false
    var isUIVisible = false
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null)
            rootView = inflater.inflate(layoutResource, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            mContext = it
        }
        views?.forEach { it.setOnClickListener(this) }
        isViewCreated = true
        lazyLoad()

    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            isUIVisible = true
            lazyLoad()
        } else {
            isUIVisible = false
        }
    }

    private fun lazyLoad() {
        if (isViewCreated && isUIVisible) {
            Log.d("当前Fragment", "==》 (${javaClass.simpleName}.kt:1)")
            if (isLoadData) {
                refreshUi()
            } else {
                afterInitView()
                isLoadData = true
            }
        }
    }


    //获取布局文件
    protected abstract val layoutResource: Int

    //初始化view
    protected abstract fun afterInitView()


    //点击事件view列表
    protected abstract val views: List<View>?


    //重新回到当前fragment刷新Data
    open fun refreshUi() {}


    override fun onDestroyView() {
        super.onDestroyView()
        ViseHttp.cancelAll()
        rootView = null
    }

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
