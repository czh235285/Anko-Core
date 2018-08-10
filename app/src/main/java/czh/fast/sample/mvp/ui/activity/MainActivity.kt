package czh.fast.sample.mvp.ui.activity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.view.KeyEvent
import android.view.View
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import czh.fast.lib.utils.AppManager
import czh.fast.lib.utils.toast
import czh.fast.sample.R
import czh.fast.sample.base.BaseActivity
import czh.fast.sample.mvp.ui.fragment.DbFragment
import czh.fast.sample.mvp.model.TabEntity
import czh.fast.sample.mvp.ui.fragment.NetFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override val layoutId: Int= R.layout.activity_main

    override val views: List<View>? get() = null

    private val mTitles = arrayOf("网络", "数据库")
    private val mIconUnSelectIds = intArrayOf(R.mipmap.tab_massage_normal, R.mipmap.tab_optimization_normal)
    private val mIconSelectIds = intArrayOf(R.mipmap.tab_massage_selected, R.mipmap.tab_optimization_selected)
    private val mTabEntities: ArrayList<CustomTabEntity> = ArrayList()

    override fun onClick(v: View?) {

    }

    override fun afterInitView() {
        (0 until mTitles.size)
                .mapTo(mTabEntities) { TabEntity(mTitles[it], mIconSelectIds[it], mIconUnSelectIds[it]) }
        initFragments()
        viewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int) = mFragments[position]
            override fun getCount() = mFragments.size
        }
        viewPager.offscreenPageLimit = 2
        with(tab) {
            setTabData(mTabEntities)
            setOnTabSelectListener(object : OnTabSelectListener {
                override fun onTabSelect(position: Int) {
                    viewPager.setCurrentItem(position, false)
                }

                override fun onTabReselect(position: Int) {

                }
            })
        }
    }

    lateinit var mFragments: MutableList<Fragment>
    private fun initFragments() {
        mFragments = ArrayList()
        mFragments.add(NetFragment.get())
        mFragments.add(DbFragment.get())
    }

    private var mExitTime: Long = 0
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - mExitTime > 2000) {
                toast("再按一次退出程序")
                mExitTime = System.currentTimeMillis()
            } else {
                AppManager.AppExit()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}
