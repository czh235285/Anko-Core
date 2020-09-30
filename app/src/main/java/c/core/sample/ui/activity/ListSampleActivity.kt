package c.core.sample.ui.activity

import android.content.Context
import c.core.adapter.holer.AnkoViewHolder
import c.core.sample.api.apiservice
import c.core.sample.base.list.AnkoListActivity
import c.core.sample.model.ProjectData
import c.core.sample.ui.layout.TextUI
import kotlinx.coroutines.CoroutineScope
import org.jetbrains.anko.AnkoComponent


//多type布局实体类继承MultiItem即可
class ListSampleActivity : AnkoListActivity<ProjectData>() {



    override suspend fun CoroutineScope.load(pageIndex: Int) {
        val data = apiservice.getProject(pageIndex).data
        submitData(data.datas)
    }

    override fun convert(holder: AnkoViewHolder, position: Int, item: ProjectData?) {
        //多Type布局这里判断 holder.itemViewType
        holder.getAnKoUi<TextUI>()?.apply {
            tvContent.text = item?.desc
        }
    }

    override fun ui(viewType: Int): AnkoComponent<Context> {
        //多Type布局这里根据viewType返回不同即可
        return TextUI()
    }

    override var headline: String? = "简单列表页Demo"

}