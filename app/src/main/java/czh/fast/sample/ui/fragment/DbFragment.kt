package czh.fast.sample.ui.fragment

import android.graphics.Canvas
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback
import com.chad.library.adapter.base.listener.OnItemDragListener
import com.chad.library.adapter.base.listener.OnItemSwipeListener
import com.raizlabs.android.dbflow.sql.language.Select
import czh.fast.lib.utils.textString
import czh.fast.lib.utils.checkALL
import czh.fast.lib.utils.getInflaterView
import czh.fast.lib.utils.toast
import czh.fast.lib.widget.SimpleDividerDecoration
import czh.fast.sample.R
import czh.fast.lib.base.LazyFragment
import czh.fast.sample.db.User
import czh.fast.sample.db.User_Table
import czh.fast.sample.ui.adapter.DemoAdapter
import kotlinx.android.synthetic.main.fragment_db.*


class DbFragment : LazyFragment() {
    var mAdapter: DemoAdapter? = null
    override val layoutResource: Int
        get() = R.layout.fragment_db

    override fun afterInitView() {
        queryList()
        rcv.run {
            addItemDecoration(SimpleDividerDecoration(0xffe5e5e5.toInt(), 1))
            layoutManager = LinearLayoutManager(activity)
            rcv.adapter = mAdapter
        }
        tvAdd.setOnClickListener {
            if (!checkALL(arrayOf(et_ext1, et_ext2))) {
                activity?.toast("输入不能为空")
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
                activity?.toast("重复名字")
            }
        }

    }

    private fun queryList() {
        val mList = Select().from(User::class.java).queryList()
        mAdapter?.let {
            it.setNewData(mList)
        } ?: kotlin.run {
            mAdapter = DemoAdapter(R.layout.item, mList)


            mAdapter?.run {
                addHeaderView(activity?.getInflaterView(R.layout.item))
                val itemDragAndSwipeCallback = ItemDragAndSwipeCallback(this)
                val itemTouchHelper = ItemTouchHelper(itemDragAndSwipeCallback)
                itemTouchHelper.attachToRecyclerView(rcv)
                // 开启拖拽
                enableDragItem(itemTouchHelper, R.id.tvName, true);
                setOnItemDragListener(onItemDragListener())

                // 开启滑动删除
                enableSwipeItem()
                setOnItemSwipeListener(onItemSwipeListener())
            }


        }
    }

    private fun onItemSwipeListener(): OnItemSwipeListener {
        return object : OnItemSwipeListener {
            override fun onItemSwipeMoving(canvas: Canvas?, viewHolder: RecyclerView.ViewHolder?, dX: Float, dY: Float, isCurrentlyActive: Boolean) {

            }

            override fun clearView(viewHolder: RecyclerView.ViewHolder?, pos: Int) {

            }

            override fun onItemSwiped(viewHolder: RecyclerView.ViewHolder?, pos: Int) {
                val db = User()
                db.id = mAdapter!!.data[pos].id
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
