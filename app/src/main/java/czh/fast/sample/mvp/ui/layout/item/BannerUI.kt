package czh.fast.sample.mvp.ui.layout.item

import android.content.Context
import android.view.Gravity
import android.widget.TextView
import czh.fast.lib.utils.anko.mu5ViewPager
import czh.fast.lib.utils.setShape
import czh.fast.lib.widget.viewpager.Mu5ViewPager
import org.jetbrains.anko.*

class BannerUI : AnkoComponent<Context> {

    lateinit var banner: Mu5ViewPager

    lateinit var tvIndex: TextView

    override fun createView(ui: AnkoContext<Context>) = with(ui) {
        frameLayout {
            lparams(matchParent, wrapContent)
            banner = mu5ViewPager { }

            tvIndex = textView("1/3") {
                setShape("#80000000", 40f)
                setPadding(25, 5, 25, 5)
            }.lparams {
                bottomMargin = dip(16)
                rightMargin = dip(16)
                gravity = Gravity.BOTTOM or Gravity.RIGHT

            }
        }
    }
}

