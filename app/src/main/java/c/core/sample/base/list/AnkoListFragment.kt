package c.core.sample.base.list

import android.util.Log
import android.view.View
import androidx.core.view.isGone
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import c.core.adapter.entity.DslItemView
import c.core.ex.anko.generateView
import c.core.sample.base.AnkoFragment
import c.core.sample.base.safeLaunch
import c.core.sample.utils.SimpleDividerDecoration
import c.core.utils.color
import kotlinx.coroutines.CoroutineScope

/**
 *  author : czh
 *  date : 2020-7-15
 *  description : 
 */
abstract class AnkoListFragment : AnkoFragment(),
    ListDelegateAnko.IList {

    protected open val listDelegate by lazy {
        ListDelegateAnko.create(this)
    }

    val ui = RecyclerViewUI()

    override fun ankoLayout(): View {
        return ui.generateView(act)
    }

    override fun getLoadNumber(): Int? = 60

    override fun afterInitView() {
        listDelegate.onBindView(ui)
        ui.bar.isGone = true
        showLoadingView()
    }

    override val wrappedView: View?
        get() = ui.srl

    override fun onLoadRetry() {
        listDelegate.refresh()
    }

    override fun submitData(elements: List<DslItemView>?) {
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
        return SimpleDividerDecoration("#e5e5e5".color, 1)
    }

    abstract suspend fun CoroutineScope.load(pageIndex: Int)

    override fun loadData(pageIndex: Int) {
        lifecycleScope.safeLaunch {
            block = {
                load(pageIndex)
            }
            onFinally = {
                listDelegate.finishRefresh()
            }

            onError = {
                Log.d("loadData Error", "==》 (${javaClass.simpleName}.kt:1)  \n $it")
                if (pageIndex == 1) {
                    showLoadFailed()
                } else {
                    listDelegate.loadFail()
                }
            }
        }
    }

}
