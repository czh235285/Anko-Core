package czh.fast.sample.mvp.ui.activity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import android.view.KeyEvent
import czh.fast.lib.widget.Gloading
import czh.fast.lib.widget.tablayout.listener.CustomTabEntity
import czh.fast.lib.widget.tablayout.listener.OnTabSelectListener
import czh.fast.sample.R

import czh.fast.sample.mvp.ui.layout.activity.MainUI
import org.jetbrains.anko.setContentView


import czh.fast.sample.base.AnkoActivity
import czh.fast.sample.mvp.model.TabEntity
import czh.fast.sample.mvp.ui.fragment.DbFragment
import czh.fast.sample.mvp.ui.fragment.NetFragment
import czh.fast.sample.mvp.ui.fragment.OtherFragment
import czh.fast.sample.utils.AppManager
import org.jetbrains.anko.toast

class MainActivity : AnkoActivity() {


    private val mTitles = arrayOf("网络", "数据库", "其他")
    private val mIconUnSelectIds = intArrayOf(R.mipmap.tab_massage_normal, R.mipmap.tab_optimization_normal, R.mipmap.tab_other_normal)
    private val mIconSelectIds = intArrayOf(R.mipmap.tab_massage_selected, R.mipmap.tab_optimization_selected, R.mipmap.tab_other_selected)
    private val mTabEntities: ArrayList<CustomTabEntity> = ArrayList()

    val ui = MainUI()
    override fun ankoLayout() {
        ui.setContentView(this)
    }

    override fun initLoadingStatusViewIfNeed() {
        if (mHolder == null) {
            mHolder = Gloading.getDefault().wrap(ui.vp).withRetry { onLoadRetry() }
        }
    }

    override fun afterInitView() = with(ui) {
        initLoadingStatusViewIfNeed()
        showLoadingView()

        vp.postDelayed({
            showLoadSuccess()
        }, 2000)

        (mTitles.indices)
                .mapTo(mTabEntities) { TabEntity(mTitles[it], mIconSelectIds[it], mIconUnSelectIds[it]) }
        initFragments()
        vp.adapter = object : androidx.fragment.app.FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int) = mFragments[position]
            override fun getCount() = mFragments.size
        }
        vp.offscreenPageLimit = 2
        with(tab) {
            setTabData(mTabEntities)
            setOnTabSelectListener(object : OnTabSelectListener {
                override fun onTabSelect(position: Int) {
                    vp.setCurrentItem(position, false)
                }

                override fun onTabReselect(position: Int) {

                }
            })
        }
    }

    lateinit var mFragments: MutableList<androidx.fragment.app.Fragment>
    private fun initFragments() {
        mFragments = ArrayList()
        mFragments.add(NetFragment.get())
        mFragments.add(DbFragment.get())
        mFragments.add(OtherFragment.get())
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
