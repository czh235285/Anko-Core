package c.core.sample.ui.activity

import android.content.Context
import c.core.adapter.addItem
import c.core.adapter.dslAdapter
import c.core.adapter.dslItem
import c.core.adapter.entity.DslItemView
import c.core.adapter.holer.AnkoViewHolder
import c.core.sample.api.apiservice
import c.core.sample.base.list.AnkoListActivity
import c.core.sample.model.ProjectData
import c.core.sample.ui.layout.ItemUI
import c.core.sample.ui.layout.TextUI
import kotlinx.coroutines.CoroutineScope
import org.jetbrains.anko.AnkoComponent


//多type布局实体类继承MultiItem即可
class ListSampleActivity : AnkoListActivity<ProjectData>() {


    override suspend fun CoroutineScope.load(pageIndex: Int) {
        val data = apiservice.getProject(pageIndex).data
        submitData(data.datas.map {
            dslItem<TextUI> { holder, position ->
                tvContent.text = it.desc
            }
        })

        //大部分APP首页多type布局可以这样挨着写
//        ui.rcv.dslAdapter {
//            addItem<TextUI> {
//                tvContent.text = "多type布局1"
//            }
//
//            addItem<ItemUI> {
//                tv.text = "多type布局2"
//            }
//        }
    }


    override var headline: String? = "简单列表页Demo"

}