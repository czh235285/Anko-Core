package czh.fast.sample.adapter

import com.chad.library.adapter.base.BaseItemDraggableAdapter
import com.chad.library.adapter.base.BaseViewHolder

import czh.fast.sample.db.User
import kotlinx.android.synthetic.main.item.view.*

class DemoAdapter(layoutResId: Int, data: List<User>?) : BaseItemDraggableAdapter<User, BaseViewHolder>(layoutResId, data) {

    override fun convert(helper: BaseViewHolder, item: User) {
        helper.itemView.run {
            tvName.text = item.urerName
            tvAge.text = item.urerAge.toString()
        }
    }
}
