package czh.fast.sample.mvp.ui.layout.activity

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import com.flyco.tablayout.CommonTabLayout
import czh.fast.lib.utils.anko.commonTabLayout
import czh.fast.lib.utils.anko.noScrollViewPager
import czh.fast.lib.widget.NoScrollViewPager

import czh.fast.sample.mvp.ui.activity.MainActivity
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView


class MainUI : AnkoComponent<MainActivity> {
    lateinit var vp: NoScrollViewPager

    lateinit var tab: CommonTabLayout

    @SuppressLint("ResourceType")
    override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
        return verticalLayout {
            backgroundColor = Color.parseColor("#f2f2f2")
            vp = noScrollViewPager {
                id = 0x12345

            }.lparams(matchParent) {
                backgroundColor = Color.parseColor("#ffffff")
                weight = 1f
            }
            cardView {
                backgroundColor = Color.parseColor("#ffffff")
                cardElevation = 8f
                tab = commonTabLayout {
                    iconHeight = 23f
                    iconWidth = 23f
                    textSelectColor = Color.parseColor("#3F51B5")
                    textUnselectColor = Color.parseColor("#666666")
                    textsize = 12f
                    indicatorHeight = 0f
                }.lparams(matchParent, dip(54))
            }


        }
    }

}
