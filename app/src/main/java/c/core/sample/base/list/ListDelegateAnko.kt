package c.core.sample.base.list

import android.content.Context
import android.view.View
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import c.core.adapter.AnkoAdapter
import c.core.adapter.entity.DslItemView
import c.core.adapter.loadmore.AnkoLoadMoreModule
import c.core.sample.R
import c.core.widget.SimpleLoadMoreView

open class ListDelegateAnko(private val iList: IList) {

    protected var pageIndex = 1

    private val pageSize = 15

    private lateinit var listAdapter: AnkoAdapter

    protected open val enableEmptyView
        get() = iList.enableEmptyView()

    private lateinit var emptyView: View
    private lateinit var refreshLayout: SwipeRefreshLayout

    @CallSuper
    fun onBindView(ui: RecyclerViewUI) {
        initEmptyView(ui.rcv.context)
        createListTopView()?.let {
            ui.flTop.addView(it)
        }
        initRecycler(ui)
        refreshLayout = ui.srl
        initSmartRefreshLayout(ui.srl)
        createListBottomView()?.let {
            ui.flBottom.addView(it)
        }
    }

    private fun initEmptyView(ctx: Context) {
        emptyView = View.inflate(ctx, R.layout.layout_empty_view_core, null)
    }

    protected open fun createListTopView(): View? = iList.createListTopView()

    private fun initRecycler(ui: RecyclerViewUI) {
        val recyclerView = ui.rcv
        listAdapter = object : AnkoAdapter(), AnkoLoadMoreModule {
        }
        val context = recyclerView.context
        val layoutManager = iList.getLayoutManager() ?: LinearLayoutManager(context)
        val itemDecoration = iList.getItemDecoration()
        initRecyclerView(recyclerView, layoutManager, itemDecoration)
        initLoadMore()
    }

    /**
     * 初始化加载更多
     */
    private fun initLoadMore() {
        listAdapter.loadMoreModule.apply {
            preLoadNumber = iList.getLoadNumber() ?: 1
            if (iList.enableLoadMore()) {
                setOnLoadMoreListener {
                    loadMore()
                }
                loadMoreView = SimpleLoadMoreView()
            }
            isAutoLoadMore = iList.enableLoadMore()
            // 当自动加载开启，同时数据不满一屏时，是否继续执行自动加载更多(默认为true)
            isEnableLoadMoreIfNotFullPage = true
        }
    }

    protected open fun initRecyclerView(
        recyclerView: RecyclerView,
        defaultLayoutManager: RecyclerView.LayoutManager,
        defaultItemDecoration: RecyclerView.ItemDecoration?
    ) {
        recyclerView.adapter = listAdapter
        recyclerView.layoutManager = defaultLayoutManager
        defaultItemDecoration?.let {
            recyclerView.addItemDecoration(it)
        }
    }

    fun submitData(elements: List<DslItemView>?) {
        listAdapter.loadMoreModule.isEnableLoadMore = true
        if (pageIndex == 1) {
            listAdapter.replaceData(elements)
            if (elements.isNullOrEmpty() && enableEmptyView) {
                listAdapter.setEmptyView(emptyView)
            }
        } else {
            listAdapter.addData(elements)
        }
        if (elements.isNullOrEmpty() || elements.size < pageSize) {
            listAdapter.loadMoreModule.loadMoreEnd()
        } else {
            listAdapter.loadMoreModule.loadMoreComplete()
        }
        pageIndex++
    }

    fun clear() {
        listAdapter.mData.clear()
        listAdapter.notifyDataSetChanged()
    }

    protected open fun initSmartRefreshLayout(refreshLayout: SwipeRefreshLayout) {
        refreshLayout.apply {
            val enableRefresh = iList.enableRefresh()
            if (iList.enableAutoRefresh()) {
                postDelayed({
                    refresh()
                }, 200)
            }
            isEnabled = enableRefresh
            setOnRefreshListener {
                refresh()
            }
        }
    }

    fun refresh() {
        pageIndex = 1
        // 这里的作用是防止下拉刷新的时候还可以上拉加载
        listAdapter.loadMoreModule.isEnableLoadMore = false
        iList.loadData(pageIndex)
    }

    fun loadMore() {
        iList.loadData(pageIndex)
    }

    fun finishRefresh() {
        refreshLayout.postDelayed({
            refreshLayout.isRefreshing = false
        }, 500)
    }

    fun loadFail() {
        listAdapter.loadMoreModule.isEnableLoadMore = true
        listAdapter.loadMoreModule.loadMoreFail()
    }

    protected open fun createListBottomView(): View? = iList.createListBottomView()

    companion object {

        fun create(iList: IList): ListDelegateAnko {
            return ListDelegateAnko(iList)
        }
    }

    interface IList {

        fun loadData(pageIndex: Int)

        fun submitData(elements: List<DslItemView>?)

        fun getLoadNumber(): Int?

        fun createListTopView(): View?

        fun createListBottomView(): View?

        fun enableEmptyView(): Boolean

        fun enableAutoRefresh(): Boolean

        fun enableRefresh(): Boolean

        fun enableLoadMore(): Boolean

        fun getLayoutManager(): RecyclerView.LayoutManager?

        fun getItemDecoration(): RecyclerView.ItemDecoration?
    }
}
