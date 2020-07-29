package c.core.sample.ui.activity

import android.view.KeyEvent
import c.core.ex.bind
import c.core.ex.px
import c.core.sample.R
import c.core.sample.base.AnkoActivity
import c.core.sample.ui.fragment.SecondFragment
import c.core.sample.ui.fragment.FirstFragment
import c.core.sample.ui.fragment.ThirdFragment
import c.core.sample.ui.layout.MainActivityUI
import c.core.sample.utils.AppManager
import c.core.utils.color
import c.core.widget.indicator.bind
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.toast

class MainActivity : AnkoActivity() {
    val ui = MainActivityUI()
    override fun ankoLayout() {
        ui.setContentView(this)
    }


    override fun afterInitView() {
        with(ui) {
            viewPager.bind(supportFragmentManager, 3) {
                return@bind when (it) {
                    1 -> SecondFragment.instance
                    2 -> ThirdFragment.instance
                    else -> FirstFragment.instance
                }
            }
            viewPager.offscreenPageLimit = 2
            magicIndicator.bind(viewPager) {
                mTitles = arrayOf("页面一", "页面二", "页面三")
                mIconUnSelectIds = intArrayOf(
                    R.mipmap.tab_massage_normal,
                    R.mipmap.tab_optimization_normal,
                    R.mipmap.tab_other_normal
                )
                mIconSelectIds = intArrayOf(
                    R.mipmap.tab_massage_selected,
                    R.mipmap.tab_optimization_selected,
                    R.mipmap.tab_other_selected
                )

                mIconHeight = 46.px
                mIconWidth = 46.px
                spacePadding = 10.px

                selectTextColor = "#3F51B5".color
                unSelectTextColor = "#666666".color
            }
        }
    }

    private var mExitTime: Long = 0
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - mExitTime > 2000) {
                toast("再按一次退出程序")
                mExitTime = System.currentTimeMillis()
            } else {
                AppManager.appExit()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}