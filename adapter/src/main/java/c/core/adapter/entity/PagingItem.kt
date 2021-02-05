package c.core.adapter.entity

import c.core.adapter.holder.BaseViewHolder

/**
 *  author : czh
 *  date : 2020/12/28 16:14
 *  description : 
 */
open class PagingItem {

    var layoutId: Int = -1

    var data: Any? = null

    var spanSize = 1

    var itemBind: (holder: BaseViewHolder, position: Int) -> Unit = { holder, position ->

    }

    fun areItemsTheSame(oldItem: PagingItem, newItem: PagingItem): Boolean {

        return false
    }
}