package czh.fast.sample.mvp.ui.adapter

import android.content.Context
import czh.adapter.AnkoAdapter
import czh.adapter.holer.BaseViewHolder
import czh.fast.sample.db.User
import czh.fast.sample.mvp.ui.layout.item.DbItemUI
import org.jetbrains.anko.AnkoComponent

class UserAdapter(ui: DbItemUI, data: List<User>?) : AnkoAdapter<User>(ui, data) {
    override fun convert(holder: BaseViewHolder, ui: AnkoComponent<Context>, item: User?) {
        with(ui as DbItemUI){
            tvName.text = item?.urerName
            tvAge.text = item?.urerAge.toString()
        }
    }
}


