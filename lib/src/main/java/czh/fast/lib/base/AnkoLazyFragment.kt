package czh.fast.lib.base

/**
 * Created by Dell on 2017/11/22.
 */

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.czh.library.LoadingDialog
import com.vise.xsnow.http.ViseHttp

//fragment基类
abstract class AnkoLazyFragment: Fragment(), LoadingView {

    var isViewCreated = false
    var isLoadData = false
    var isUIVisible = false
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return UI()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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


    protected abstract fun UI(): View
    //初始化view
    protected abstract fun afterInitView()


    //重新回到当前fragment刷新Data
    open fun refreshUi() {}


    override fun onDestroyView() {
        super.onDestroyView()
        ViseHttp.cancelAll()
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
