package c.core.widget

import android.content.Context
import android.view.View
import c.core.adapter.holer.AnkoViewHolder
import c.core.adapter.loadmore.AnKoLoadMoreView
import c.core.layout.LoadMoreViewUI
import org.jetbrains.anko.AnkoComponent

class SimpleLoadMoreView : AnKoLoadMoreView() {
    override fun getRootView(): AnkoComponent<Context> {
        return LoadMoreViewUI()
    }

    override fun getLoadingView(holder: AnkoViewHolder): View? {
        return holder.getAnKoUi<LoadMoreViewUI>()?.loadingView
    }

    override fun getLoadComplete(holder: AnkoViewHolder): View? {
        return holder.getAnKoUi<LoadMoreViewUI>()?.loadCompleteView
    }

    override fun getLoadEndView(holder: AnkoViewHolder): View? {
        return holder.getAnKoUi<LoadMoreViewUI>()?.loadEndView
    }

    override fun getLoadFailView(holder: AnkoViewHolder): View? {
        return holder.getAnKoUi<LoadMoreViewUI>()?.loadFailView
    }
}