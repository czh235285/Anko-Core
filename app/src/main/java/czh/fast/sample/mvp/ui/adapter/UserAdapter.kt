package czh.fast.sample.mvp.ui.adapter

import android.content.Context
import czh.adapter.AnkoAdapter
import czh.adapter.holer.AnkoViewHolder
import czh.fast.sample.db.User
import czh.fast.sample.mvp.ui.layout.item.DbItemUI
import org.jetbrains.anko.AnkoComponent

class UserAdapter(data: List<User>?) : AnkoAdapter<User>(data) {
    override fun ankoLayout(viewType: Int): AnkoComponent<Context> {
        return DbItemUI()
    }

    override fun convert(holder: AnkoViewHolder, position: Int, item: User?) = with(holder.ui as DbItemUI) {
        tvName.text = item?.urerName
        tvAge.text = item?.urerAge.toString()
    }
}


