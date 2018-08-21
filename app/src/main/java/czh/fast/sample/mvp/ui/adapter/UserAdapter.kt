package czh.fast.sample.mvp.ui.adapter

import czh.adapter.AnkoAdapter
import czh.adapter.BaseViewHolder
import czh.fast.sample.db.User
import czh.fast.sample.mvp.ui.layout.item.DbItemUI

class UserAdapter(ui: DbItemUI, data: List<User>?) : AnkoAdapter<DbItemUI, User>(ui, data) {

    override fun convert(holder: BaseViewHolder, ui: DbItemUI, item: User?) = with(ui) {
        tvName.text = item?.urerName
        tvAge.text = item?.urerAge.toString()
    }
}


