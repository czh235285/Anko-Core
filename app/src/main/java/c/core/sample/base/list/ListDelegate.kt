package c.core.sample.base.list

import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import c.core.adapter.BaseListAdapter
import c.core.adapter.holer.BaseViewHolder
import c.core.adapter.loadmore.LoadMoreModule
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.constant.RefreshState
import com.vise.log.ViseLog
import c.core.sample.R

open class ListDelegate<T>(private val iList: IList<T>) {

    protected var pageIndex = 1

    private val pageSize = 15


    private lateinit var listAdapter: BaseListAdapter<T>

    protected open val enableEmptyView
        get() = iList.enableEmptyView()

    private lateinit var emptyView: View
    private lateinit var refreshLayout: SmartRefreshLayout

    @CallSuper
    fun onBindView(content: LinearLayout?) {
        if (content == null) {
            return
        }
        initEmptyView(content)
//        iList.initTopTitleBar(content.findViewById(R.id.titleBar))
        createListTopView()?.let {
            content.findViewById<FrameLayout>(R.id.flTop).addView(it)
        }
        initRecycler(content)
        initRefresh(content)
        createListBottomView()?.let {
            content.findViewById<FrameLayout>(R.id.flBottom).addView(it)
        }
    }

    private fun initEmptyView(content: LinearLayout) {
        emptyView = View.inflate(content.context, R.layout.layout_empty_view_core, null)
    }

    protected open fun createListTopView(): View? = iList.createListTopView()

    private fun initRecycler(content: LinearLayout) {
        val recyclerView = content.findViewById<RecyclerView>(R.id.recyclerView)
        listAdapter = object : BaseListAdapter<T>(null), LoadMoreModule {
            override fun convert(holder: BaseViewHolder, position: Int, item: T?) {
                iList.convert(holder, position, item)
            }

            override fun ui(viewType: Int): Int {
                return iList.ui(viewType)
            }

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
            preLoadNumber = iList.getLoadNumber()?:1
            setOnLoadMoreListener { loadMore() }
            isAutoLoadMore = iList.enableLoadMore()
            //当自动加载开启，同时数据不满一屏时，是否继续执行自动加载更多(默认为true)
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

    fun submitData(elements: List<T>?) {
        pageIndex++
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
            refreshLayout.postDelayed({
                refreshLayout.finishLoadMoreWithNoMoreData()
            }, 100)
        } else {
            listAdapter.loadMoreModule.loadMoreComplete()
        }
    }


    fun clear() {
        listAdapter.mData.clear()
        listAdapter.notifyDataSetChanged()
    }

    private fun initRefresh(content: LinearLayout) {
        refreshLayout = content.findViewById(R.id.smartRefreshLayout)
        initSmartRefreshLayout(refreshLayout)

    }

    protected open fun initSmartRefreshLayout(refreshLayout: SmartRefreshLayout) {
        refreshLayout.apply {
            val enableRefresh = iList.enableRefresh()
            if (iList.enableAutoRefresh()) {
                postDelayed({
                    refresh()
                }, 200)
//                if (enableRefresh) {
//                    autoRefresh(0, 200, 1F, false)
//                } else {
//                    postDelayed({
//                        refresh()
//                    }, 200)
//                }
            }
            setEnableLoadMore(false)
            setEnableRefresh(enableRefresh)
            setOnRefreshListener {
                when (it.state) {
                    RefreshState.Refreshing -> refresh()
                    else -> ViseLog.i(it)
                }
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
        refreshLayout.finishRefresh()
    }

    fun finishLoadMore() {
        refreshLayout.finishLoadMore(100)
    }

    fun loadFail() {
        listAdapter.loadMoreModule.isEnableLoadMore = true
        listAdapter.loadMoreModule.loadMoreFail()
    }

    protected open fun createListBottomView(): View? = iList.createListBottomView()

    companion object {

        fun <T> create(iList: IList<T>): ListDelegate<T> {
            return ListDelegate(iList)
        }

    }

    interface IList<T> {
        fun convert(holder: BaseViewHolder, position: Int, item: T?)

        fun ui(viewType: Int): Int

        fun loadData(pageIndex: Int)

        fun submitData(elements: List<T>?)

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
