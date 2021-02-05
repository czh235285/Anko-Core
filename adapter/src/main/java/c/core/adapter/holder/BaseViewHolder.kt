package c.core.adapter.holder


import androidx.recyclerview.widget.RecyclerView
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

open class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun <VB : ViewDataBinding> bind(action: VB.() -> Unit) {
        DataBindingUtil.bind<VB>(itemView)?.also {
            action.invoke(it)
            it.executePendingBindings()
        }
    }
}