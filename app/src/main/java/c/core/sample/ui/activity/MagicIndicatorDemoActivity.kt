package c.core.sample.ui.activity

import androidx.activity.viewModels
import c.core.ex.bind
import c.core.ex.px
import c.core.ex.pxf
import c.core.sample.base.AnkoActivity
import c.core.sample.ui.fragment.SampleFragment
import c.core.sample.ui.layout.MagicIndicatorDemoActivityUI
import c.core.sample.ui.viewmodel.MagicIndicatorViewModel
import c.core.widget.indicator.bind
import c.core.widget.indicator.configIndicator
import c.core.widget.indicator.configPadding
import c.core.widget.indicator.configText
import org.jetbrains.anko.setContentView

class MagicIndicatorDemoActivity : AnkoActivity() {

    private val viewModel by viewModels<MagicIndicatorViewModel>()

    val ui = MagicIndicatorDemoActivityUI()
    override fun ankoLayout() {
        ui.setContentView(this)
    }

    override fun afterInitView() {
        ui.apply {
            viewPager.bind(supportFragmentManager, viewModel.mList.size) {
                SampleFragment.newInstance(it)
            }

            magicIndicator.bind(viewPager, viewModel.mList) {
                configIndicator {
                    radius = 3.pxf
                    height = 6.pxf
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
