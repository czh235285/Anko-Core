package czh.fast.sample.mvp.ui.fragment

import android.graphics.Canvas
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback
import com.chad.library.adapter.base.listener.OnItemDragListener
import com.chad.library.adapter.base.listener.OnItemSwipeListener
import com.raizlabs.android.dbflow.sql.language.Select
import czh.fast.lib.utils.*
import czh.fast.lib.widget.SimpleDividerDecoration
import czh.fast.sample.R
import czh.fast.sample.base.LazyFragment
import czh.fast.sample.db.User
import czh.fast.sample.db.User_Table
import czh.fast.sample.mvp.ui.adapter.DemoAdapter
import kotlinx.android.synthetic.main.fragment_db.*


class DbFragment : LazyFragment() {

    val mAdapter by lazy {
        DemoAdapter(R.layout.item, mList)
    }

    private var mList = arrayListOf<User>()

    override val layoutResource: Int= R.layout.fragment_db

    override val views: List<View>? get() = null

    override fun onClick(v: View?) {

    }

    override fun afterInitView() {
        mAdapter.run {
            addHeaderView(mContext.inflater(R.layout.item))
            val itemDragAndSwipeCallback = ItemDragAndSwipeCallback(this)
            val itemTouchHelper = ItemTouchHelper(itemDragAndSwipeCallback)
            itemTouchHelper.attachToRecyclerView(rcv)
            // 开启拖拽
            enableDragItem(itemTouchHelper, R.id.tvName, true)
            setOnItemDragListener(onItemDragListener())

            // 开启滑动删除
            enableSwipeItem()
            setOnItemSwipeListener(onItemSwipeListener())
        }
        rcv.run {
            addItemDecoration(SimpleDividerDecoration(0xffe5e5e5.toInt(), 1))
            layoutManager = LinearLayoutManager(activity)
            rcv.adapter = mAdapter
        }
        queryList()
        tvAdd.setOnClickListener {
            if (!checkALL(et_ext1, et_ext2)) {
                mContext.toast("输入不能为空")
                return@setOnClickListener
            }


            val mList = Select().from(User::class.java).where(User_Table.urerName.`is`(et_ext1.textString())).queryList()
            if (mList.size == 0) {
                val db = User()
                db.urerName = et_ext1.textString()
                db.urerAge = et_ext2.textString().toInt()
                db.save()
                queryList()
            } else {
                mContext.toast("重复名字")
            }
        }

    }

    private fun queryList() {
        val mList = Select().from(User::class.java).queryList()
        mAdapter.setNewData(mList)
    }

    private fun onItemSwipeListener(): OnItemSwipeListener {
        return object : OnItemSwipeListener {
            override fun onItemSwipeMoving(canvas: Canvas?, viewHolder: RecyclerView.ViewHolder?, dX: Float, dY: Float, isCurrentlyActive: Boolean) {

            }

            override fun clearView(viewHolder: RecyclerView.ViewHolder?, pos: Int) {

            }

            override fun onItemSwiped(viewHolder: RecyclerView.ViewHolder?, pos: Int) {
                val db = User()
                db.id = mAdapter.data[pos].id
                db.delete()
            }

            override fun onItemSwipeStart(viewHolder: RecyclerView.ViewHolder?, pos: Int) {
            }
        }
    }

    private fun onItemDragListener(): OnItemDragListener {
        return object : OnItemDragListener {
            override fun onItemDragMoving(source: RecyclerView.ViewHolder?, from: Int, target: RecyclerView.ViewHolder?, to: Int) {
            }

            override fun onItemDragStart(viewHolder: RecyclerView.ViewHolder?, pos: Int) {
            }

            override fun onItemDragEnd(viewHolder: RecyclerView.ViewHolder?, pos: Int) {
            }
        }
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
