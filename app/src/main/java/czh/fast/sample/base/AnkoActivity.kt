package czh.fast.sample.base


import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Window
import android.view.WindowManager
import czh.fast.lib.R
import czh.fast.lib.base.LoadingView
import czh.fast.lib.utils.DensityUtils
import czh.fast.lib.utils.status.LightStatusBarUtils
import czh.fast.lib.utils.status.RomUtils
import czh.fast.lib.utils.status.StatusBarUtil
import czh.fast.lib.utils.status.setStatusBarByColorRes
import czh.fast.lib.widget.LoadingDialog
import czh.fast.sample.utils.AppManager

//Activity基类
abstract class AnkoActivity : AppCompatActivity(), LoadingView {

    override fun onStart() {
        super.onStart()
        Log.d("当前Activity", "==》 (${javaClass.simpleName}.kt:1)")
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DensityUtils.setCustomDensity(this, application)
        doBeforeSetContentView()
        ankoLayout()
        //设置状态栏颜色
        setStatusBarByColorRes(R.color.colorPrimary)
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



    //初始化view
    protected abstract fun afterInitView()

    protected abstract fun ankoLayout()
    //浅色状态栏
    fun setLightStatusBar() {
        StatusBarUtil.setColor(this, 0xffffffff.toInt(), 0)
        if (RomUtils.isLightStatusBarAvailable) {
            LightStatusBarUtils.setLightStatusBar(this, true)
        } else {
            StatusBarUtil.setColor(this, 0xff363636.toInt())
        }
    }

    private var mLoading: LoadingDialog? = null
    override fun showLoading() {
        if (mLoading == null) {
            mLoading = LoadingDialog(this)
        }
        mLoading?.show()
    }

    override fun hideLoading() {
        mLoading?.dismiss()
    }


    override fun onDestroy() {
        super.onDestroy()
        AppManager.appManager.finishActivity(this)
    }
}
