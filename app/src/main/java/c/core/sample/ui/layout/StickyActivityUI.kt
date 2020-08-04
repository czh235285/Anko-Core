package c.core.sample.ui.layout

import android.view.Gravity
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import c.core.ex.anko.appBarLayout
import c.core.ex.anko.coordinatorLayout
import c.core.ex.px
import c.core.ex.text
import c.core.sample.ui.activity.StickyActivity
import c.core.utils.color
import c.core.widget.recyclerView
import c.core.widget.toolbar.ankoToolBar
import com.google.android.material.appbar.AppBarLayout
import org.jetbrains.anko.*


/**
 *  author : czh
 *  date : 2020-7-29
 *  description : 
 */
class StickyActivityUI : AnkoComponent<StickyActivity> {
    lateinit var rcv: RecyclerView
    lateinit var appbar: AppBarLayout

    override fun createView(ui: AnkoContext<StickyActivity>): View = with(ui) {
        verticalLayout {
            ankoToolBar { title = "吸顶demo" }
            coordinatorLayout {
                backgroundColor = "#ffffff".color
                appbar = appBarLayout {
                    text(20) {
                        backgroundColor = "#f0f0f0".color
                    }.lparams(-1, 400.px) {

                    }
                    text(20, content = "吸顶部分") {
                        gravity = Gravity.CENTER
                        backgroundColor = "#f00000".color
                    }.lparams(-1, 100.px) {
                        scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
                    }

                }.lparams(-1)

                rcv = recyclerView {

                }.lparams(-1) {
                    behavior = AppBarLayout.ScrollingViewBehavior()
                }
            }.lparams(-1, -1)


        }
    }
}