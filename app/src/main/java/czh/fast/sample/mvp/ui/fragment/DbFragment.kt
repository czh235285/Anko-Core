package czh.fast.sample.mvp.ui.fragment

import android.view.View
import com.raizlabs.android.dbflow.sql.language.Select
import czh.fast.lib.utils.anko.setContentView
import czh.fast.sample.base.AnkoFragment
import czh.fast.sample.mvp.ui.layout.fragment.DbUI


import czh.fast.sample.db.User
import czh.fast.sample.mvp.ui.adapter.UserAdapter
import czh.fast.sample.mvp.ui.layout.item.DbItemUI
import czh.fast.sample.mvp.ui.layout.item.EmptyUI

class DbFragment : AnkoFragment() {


    private var mList = Select().from(User::class.java).queryList()
    val mAdapter by lazy {
        UserAdapter(mList).apply {
            addHeaderView(DbItemUI().setContentView(requireActivity()))
            setEmptyView(EmptyUI().setContentView(requireActivity()))
        }
    }

    val ui = DbUI()


    override fun layout(): View {
        return ui.setContentView(this)
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
