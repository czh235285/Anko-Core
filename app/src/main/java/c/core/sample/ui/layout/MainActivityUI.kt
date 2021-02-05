package c.core.sample.ui.layout

import androidx.viewpager.widget.ViewPager
import c.core.ex.anko.noScrollViewPager
import c.core.ex.px
import c.core.sample.ui.activity.MainActivity
import c.core.sample.utils.Ids
import c.core.widget.indicator.magicIndicator
import c.core.widget.line
import net.lucode.hackware.magicindicator.MagicIndicator
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.verticalLayout

class MainActivityUI : AnkoComponent<MainActivity> {
    lateinit var magicIndicator: MagicIndicator
    lateinit var viewPager: ViewPager

    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        verticalLayout {
            viewPager = noScrollViewPager {
                id = Ids.viewPager
            }.lparams(-1, 0, 1f)
            line {}.lparams(-1, 1)
            magicIndicator = magicIndicator {
            }.lparams(-1, 133.px)
        }
    }
}
