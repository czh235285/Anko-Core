package c.core.sample.ui.fragment

import android.content.Context
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import c.core.adapter.holer.AnkoViewHolder
import c.core.sample.base.list.AnkoListFragment
import c.core.sample.ui.activity.GLoadingActivity
import c.core.sample.ui.activity.ListSampleActivity
import c.core.sample.ui.layout.ItemUI
import c.core.sample.ui.viewmodel.FirstViewModel
import c.core.utils.openActivity
import c.core.utils.throttleClick
import kotlinx.coroutines.CoroutineScope
import org.jetbrains.anko.AnkoComponent

class FirstFragment private constructor() : AnkoListFragment<String>() {


    private val viewModel by viewModels<FirstViewModel>()

    override fun afterInitView() {
        lifecycle.addObserver(viewModel)
        super.afterInitView()
    }

    override suspend fun CoroutineScope.load(pageIndex: Int) {
        submitData(
            listOf(
                "分页加载\n几行代码实现错误重试，预加载等等...",
                "状态管理", "viewModel调用loading"
            )
        )
    }

    override fun convert(holder: AnkoViewHolder, position: Int, item: String?) {
        holder.getAnKoUi<ItemUI>()?.apply {
            tv.text = item
            holder.itemView.throttleClick {
                when (position) {
                    0 -> openActivity<ListSampleActivity>()
                    1 -> openActivity<GLoadingActivity>()
                    2 -> viewModel.test()
                    else -> {

                    }
                }
            }
        }
    }

    override fun ui(viewType: Int): AnkoComponent<Context> {
        return ItemUI()
    }

    override fun enableLoadMore(): Boolean {
        return false
    }

    override fun enableRefresh(): Boolean {
        return false
    }

    override fun getItemDecoration(): RecyclerView.ItemDecoration? {
        return null
    }

    companion object {
        val instance: FirstFragment = FirstFragment()
    }

}
