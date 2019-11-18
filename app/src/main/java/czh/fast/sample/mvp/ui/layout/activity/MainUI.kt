package czh.fast.sample.mvp.ui.layout.activity

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import czh.fast.lib.utils.anko.commonTabLayout
import czh.fast.lib.utils.anko.noScrollViewPager
import czh.fast.lib.widget.NoScrollViewPager
import czh.fast.lib.widget.tablayout.CommonTabLayout

import czh.fast.sample.mvp.ui.activity.MainActivity
import czh.fast.sample.utils.px
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
                tab = commonTabLayout("#3F51B5", "#666666", 12f, 23f, 23f).lparams(matchParent, 98.px)
            }


        }
    }

}
