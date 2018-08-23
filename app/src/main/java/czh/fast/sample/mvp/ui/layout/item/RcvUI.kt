package czh.fast.sample.mvp.ui.layout.item

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import czh.fast.lib.utils.anko.recyclerView
import org.jetbrains.anko.*

class RcvUI : AnkoComponent<Context> {
    lateinit var rcv: RecyclerView

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        verticalLayout {
            lparams(matchParent, wrapContent)
            textView("热门分享") {
                textColor = Color.parseColor("#333333")
                setPadding(30, 30, 30, 30)
            }
            rcv = recyclerView {
                val lm = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                lm.isAutoMeasureEnabled = true
                layoutManager = lm
            }
        }
    }
}