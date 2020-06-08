package czh.fast.sample.mvp.ui.layout.fragment

import android.graphics.Color
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import czh.fast.lib.utils.anko.itemLayout
import czh.fast.lib.bean.ItemBean
import czh.fast.lib.utils.openActivity
import czh.fast.lib.widget.SimpleDividerDecoration
import czh.fast.sample.R
import czh.fast.sample.mvp.ui.activity.AdaptationActivity
import czh.fast.sample.mvp.ui.activity.ImageActivity
import czh.fast.sample.mvp.ui.activity.MultiActivity
import czh.fast.sample.mvp.ui.fragment.OtherFragment
import czh.fast.sample.utils.ankoToolBar
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class OtherFragmentUI : AnkoComponent<OtherFragment> {

    lateinit var rcv: androidx.recyclerview.widget.RecyclerView

    override fun createView(ui: AnkoContext<OtherFragment>): View = with(ui) {
        return verticalLayout {

            ankoToolBar {
                title = "通用item布局"
                titleColor = Color.parseColor("#ffffff")
                backgroundColorResource = R.color.colorPrimaryDark
                hideNavigation()
            }
            view {
                backgroundColor = Color.parseColor("#eeeeee")
            }.lparams(matchParent, 1)
            rcv = recyclerView {
                layoutManager = androidx.recyclerview.widget.LinearLayoutManager(ctx)
                addItemDecoration(SimpleDividerDecoration(Color.parseColor("#e5e5e5"), 1))
            }

        }
    }
}