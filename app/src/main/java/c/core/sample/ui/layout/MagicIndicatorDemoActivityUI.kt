package c.core.sample.ui.layout

import androidx.viewpager.widget.ViewPager
import c.core.ex.anko.viewPager
import c.core.ex.px
import c.core.sample.ui.activity.MagicIndicatorDemoActivity
import c.core.sample.utils.Ids
import c.core.widget.indicator.magicIndicator
import c.core.widget.toolbar.ankoToolBar
import net.lucode.hackware.magicindicator.MagicIndicator
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.verticalLayout

class MagicIndicatorDemoActivityUI : AnkoComponent<MagicIndicatorDemoActivity> {
    lateinit var magicIndicator: MagicIndicator
    lateinit var viewPager: ViewPager

    override fun createView(ui: AnkoContext<MagicIndicatorDemoActivity>) = with(ui) {
        verticalLayout {
            ankoToolBar {
                title = "MagicIndicatorDemo"
            }
            magicIndicator = magicIndicator {

            }.lparams(-1, 70.px)

            viewPager = viewPager {
                id = Ids.viewPager
            }
        }
    }
}