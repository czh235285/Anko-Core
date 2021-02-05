package c.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import c.core.adapter.entity.PagingItem
import c.core.adapter.holder.BaseViewHolder

/**
 *  author : czh
 *  date : 2020/12/28 16:03
 *  description : 
 */
class PagingAdapter : PagingDataAdapter<PagingItem, BaseViewHolder>(object :
    DiffUtil.ItemCallback<PagingItem>() {
    override fun areItemsTheSame(oldItem: PagingItem, newItem: PagingItem): Boolean {
        if (oldItem.layoutId != newItem.layoutId) {
            return false
        }
        TODO("Not yet implemented")
    }

    override fun areContentsTheSame(oldItem: PagingItem, newItem: PagingItem): Boolean {
        TODO("Not yet implemented")
    }
}
) {
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        getItem(position)?.itemBind?.invoke(holder, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BaseViewHolder(layoutInflater.inflate(viewType, null))
    }
}