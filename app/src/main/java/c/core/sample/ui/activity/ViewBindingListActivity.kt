package c.core.sample.ui.activity

import c.core.adapter.holer.BaseViewHolder
import c.core.sample.R
import c.core.sample.api.apiservice
import c.core.sample.base.list.BaseListActivity
import c.core.sample.databinding.ItemTextBinding
import c.core.sample.model.ProjectData
import kotlinx.coroutines.CoroutineScope

class ViewBindingListActivity : BaseListActivity<ProjectData>() {

    override fun ui(viewType: Int): Int {
        return R.layout.item_text
    }

    override fun convert(holder: BaseViewHolder, position: Int, item: ProjectData?) {
        ItemTextBinding.bind(holder.itemView).apply {
            tv.text = item?.desc
        }
    }

    override suspend fun CoroutineScope.load(pageIndex: Int) {
        val data = apiservice.getProject(pageIndex).data
        submitData(data.datas)
    }
}
