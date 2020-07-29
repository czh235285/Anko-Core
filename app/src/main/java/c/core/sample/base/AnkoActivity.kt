package c.core.sample.base


import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.leaf.library.StatusBarUtil
import c.core.sample.utils.AppManager
import c.core.utils.LightStatusBarUtils
import c.core.utils.color
import c.core.widget.status.Gloading
import c.core.widget.LoadingDialog


//Activity基类
abstract class AnkoActivity : AppCompatActivity() {


    override fun onStart() {
        super.onStart()
        Log.d("当前Activity", "==》 (${javaClass.simpleName}.kt:1)")
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doBeforeSetContentView()
        ankoLayout()
        //设置状态栏颜色
        setLightStatusBar()
        initLoadingStatusView()
        afterInitView()
    }


    /**
     * 设置layout前配置
     */
    private fun doBeforeSetContentView() {
        // 把actvity放到application栈中管理
        AppManager.appManager.addActivity(this)
        // 无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        // 设置竖屏
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        // 默认不自动弹起软键盘
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }


    protected abstract fun ankoLayout()

    //初始化view
    protected abstract fun afterInitView()


    //浅色状态栏
    fun setLightStatusBar() {
        StatusBarUtil.setColor(this, 0xffffffff.toInt(), 0)
//        StatusBarUtil.setLightMode(this)
        LightStatusBarUtils.setLightStatusBar(this, true)
    }

    private var mLoading: LoadingDialog? = null
    fun showLoading() {
        if (mLoading == null) {
            mLoading = LoadingDialog(this)
        }
        mLoading?.show()
    }

    fun hideLoading() {
        mLoading?.dismiss()
    }


    override fun onDestroy() {
        super.onDestroy()
        AppManager.finishActivity(this)
    }


    /** 被状态布局包裹的View **/
    protected open val wrappedView: View? = null

    private fun initLoadingStatusView() {
        if (mHolder == null) {
            mHolder = if (wrappedView != null) {
                Gloading.getDefault().wrap(wrappedView).withRetry { onLoadRetry() }
            } else {
                Gloading.getDefault().wrap(this).withRetry { onLoadRetry() }
            }
        }
    }

    protected open fun onLoadRetry() {

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
