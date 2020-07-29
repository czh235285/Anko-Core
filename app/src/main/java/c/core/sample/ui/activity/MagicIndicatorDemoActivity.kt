package c.core.sample.ui.activity

import c.core.ex.bind
import c.core.ex.px
import c.core.ex.pxf
import c.core.sample.base.AnkoActivity
import c.core.sample.ui.fragment.SampleFragment
import c.core.sample.ui.fragment.ThirdFragment
import c.core.sample.ui.layout.MagicIndicatorDemoActivityUI
import c.core.widget.indicator.*
import org.jetbrains.anko.setContentView

class MagicIndicatorDemoActivity : AnkoActivity() {


    val ui = MagicIndicatorDemoActivityUI()
    private val mList = arrayOf("测试1", "测试2", "测试3", "测试4", "测试5", "测试6", "测试7", "测试8", "测试9")
    override fun ankoLayout() {
        ui.setContentView(this)
    }

    override fun afterInitView() {
        ui.apply {
            viewPager.bind(supportFragmentManager, mList.size) {
                SampleFragment.newInstance(mList[it])
            }

            magicIndicator.bind(viewPager, mList) {
                configIndicator {
                    radius = 3.pxf
                    height = 6.pxf

//                    isUseShape = true
//                    radius = 10.pxf
//                    xOffset = 10.pxf
//                    yOffset = 5.pxf
                }
                configText {
                    selectTextSize = 29.px
                    unSelectTextSize = 29.px
                }
                configPadding {
                    tabPadding = 30.px
                    titlePadding = 30.px
                }
            }
        }
    }
}