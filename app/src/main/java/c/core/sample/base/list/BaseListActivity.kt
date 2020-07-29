package c.core.sample.base.list

import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import c.core.sample.R
import c.core.sample.base.BaseActivity
import c.core.sample.base.safeLaunch
import c.core.sample.databinding.ActivityBaseListBinding
import kotlinx.coroutines.CoroutineScope

/**
 *  author : czh
 *  date : 2020-7-15
 *  description : 
 */
abstract class BaseListActivity<T> : BaseActivity<ActivityBaseListBinding>(),
    ListDelegate.IList<T> {

    protected open val listDelegate by lazy {
        ListDelegate.create(this)
    }

    override fun initContentView() {
        binding = ActivityBaseListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override val layout = R.layout.activity_base_list

    override fun getLoadNumber(): Int? = 60


    override val wrappedView: View?
        get() = binding.smartRefreshLayout

    override fun onLoadRetry() {
        listDelegate.refresh()
    }

    override fun afterInitView() {
        listDelegate.onBindView(binding.layoutListContent)
        showLoadingView()
    }


    override fun submitData(elements: List<T>?) {
        showLoadSuccess()
        listDelegate.submitData(elements)
    }

    override fun createListTopView(): View? {
        return null
    }

    override fun createListBottomView(): View? {
        return null
    }

    override fun enableEmptyView(): Boolean {
        return true
    }

    override fun enableAutoRefresh(): Boolean {
        return true
    }

    override fun enableRefresh(): Boolean {
        return true
    }

    override fun enableLoadMore(): Boolean {
        return true
    }

    override fun getLayoutManager(): RecyclerView.LayoutManager? {
        return null
    }

    override fun getItemDecoration(): RecyclerView.ItemDecoration? {
        return null
    }

    abstract suspend fun CoroutineScope.load(pageIndex: Int)

    override fun loadData(pageIndex: Int) {
        lifecycleScope.safeLaunch {
            block = {
                load(pageIndex)
            }
            onFinally = {
                listDelegate.finishLoadMore()
                listDelegate.finishRefresh()
            }

            onError = {
                if (pageIndex == 1) {
                    showLoadFailed()
                } else {
                    listDelegate.loadFail()
                }
            }
        }
    }

}
