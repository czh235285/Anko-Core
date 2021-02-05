package c.core.sample.base.list

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import c.core.ex.anko.smartRefreshLayout
import c.core.ex.anko.swipeRefreshLayout
import c.core.widget.recyclerView
import c.core.widget.toolbar.AnkoToolBar
import c.core.widget.toolbar.ankoToolBar
import org.jetbrains.anko.*

class RecyclerViewUI : AnkoComponent<Context> {
    lateinit var srl: SwipeRefreshLayout
    lateinit var bar: AnkoToolBar
    lateinit var rcv: RecyclerView
    lateinit var flBottom: FrameLayout
    lateinit var flTop: FrameLayout

    override fun createView(ui: AnkoContext<Context>) = with(ui) {
        verticalLayout {
            bar = ankoToolBar {
            }
            flTop = frameLayout {}.lparams(-1)
            srl = swipeRefreshLayout {
                rcv = recyclerView {
                    layoutManager = LinearLayoutManager(ctx)
                }.lparams(-1)
            }.lparams(matchParent, 0, 1f)
            flBottom = frameLayout {}.lparams(-1)
        }
    }
}
