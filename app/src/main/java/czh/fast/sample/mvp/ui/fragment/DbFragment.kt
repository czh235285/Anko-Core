package czh.fast.sample.mvp.ui.fragment

import android.view.View
import com.raizlabs.android.dbflow.sql.language.Select
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx
import czh.fast.sample.mvp.ui.layout.fragment.DbUI


import czh.fast.sample.base.AnkoLazyFragment
import czh.fast.sample.db.User
import czh.fast.sample.mvp.ui.adapter.UserAdapter
import czh.fast.sample.mvp.ui.layout.item.DbItemUI
import czh.fast.sample.mvp.ui.layout.item.EmptyUI

class DbFragment : AnkoLazyFragment() {

    private var mList = Select().from(User::class.java).queryList()
    val mAdapter by lazy {
        UserAdapter(mList).apply {
            addHeaderView(DbItemUI().createView(AnkoContext.create(activity!!)))
            setEmptyView(EmptyUI().createView(AnkoContext.create(activity!!)))
        }
    }

    val ui = DbUI()

    override fun UI(): View {
        return ui.createView(AnkoContext.create(ctx, this))
    }

    override fun afterInitView() = with(ui) {


    }

    fun queryList() {
        val mList = Select().from(User::class.java).queryList()
        mAdapter.replaceData(mList)
    }

    companion object {
        fun get(): DbFragment {
            return Inner.anotherSingle
        }

        private object Inner {
            val anotherSingle = DbFragment()
        }
    }

}
