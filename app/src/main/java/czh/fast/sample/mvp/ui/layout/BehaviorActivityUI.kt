package czh.fast.sample.mvp.ui.layout

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import czh.fast.lib.utils.anko.recyclerView
import czh.fast.sample.R
import czh.fast.sample.mvp.ui.activity.BehaviorActivity
import czh.fast.sample.widget.AlphaBehavior
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.themedAppBarLayout
import org.jetbrains.anko.support.v4.nestedScrollView

class BehaviorActivityUI : AnkoComponent<BehaviorActivity> {
    lateinit var rcv: RecyclerView

    override fun createView(ui: AnkoContext<BehaviorActivity>): View = with(ui) {
        coordinatorLayout {
            appBarLayout {
                verticalLayout {
                    minimumHeight = 50
                }.lparams(-1, 100) {
                    scrollFlags =
                            AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
                }
            }

            rcv = recyclerView {
                layoutManager = LinearLayoutManager(ctx)
            }.lparams(-1) {
                behavior = AppBarLayout.ScrollingViewBehavior()
            }
            textView { }.lparams {
                behavior = AlphaBehavior()
            }
        }
    }
}