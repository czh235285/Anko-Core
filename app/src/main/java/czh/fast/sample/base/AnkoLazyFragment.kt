package czh.fast.sample.base

/**
 * Created by Dell on 2017/11/22.
 */

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import czh.fast.lib.base.LoadingView
import czh.fast.lib.widget.LoadingDialog

//fragment基类
abstract class AnkoLazyFragment: AnkoFragment(), LoadingView {

    var isViewCreated = false
    var isLoadData = false
    var isUIVisible = false

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




    //重新回到当前fragment刷新Data
    open fun refreshUi() {}


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
