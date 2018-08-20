package czh.fast.sample.mvp.ui.adapter

import android.view.View
import czh.fast.sample.db.User
import czh.fast.sample.mvp.ui.layout.item.DbItemUI
import czh.library.BaseViewHolder
import czh.library.SimpleAnkoAdapter
import org.jetbrains.anko.AnkoContext

class UserAdapter(data: List<User>?) : SimpleAnkoAdapter<User>(data) {
    val ui = DbItemUI()
    override fun ankoLayout(): View {
        return ui.createView(AnkoContext.create(mContext))
    }

    override fun convert(holder: BaseViewHolder, item: User?) = with(ui) {
        tvName.text = item?.urerName
        tvAge.text = item?.urerAge.toString()

    }

}


