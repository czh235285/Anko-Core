package c.core.sample.ui.activity

import c.core.adapter.dslItem
import c.core.sample.api.apiservice
import c.core.sample.base.list.AnkoListActivity
import c.core.sample.ui.layout.TextUI
import kotlinx.coroutines.CoroutineScope

class ListSampleActivity : AnkoListActivity() {

    override suspend fun CoroutineScope.load(pageIndex: Int) {
        val data = apiservice.getProject(pageIndex).data
        submitData(data.datas.map {
            dslItem<TextUI> { holder, position ->
                tvContent.text = it.desc
            }
        })

        // 大部分APP首页多type布局可以这样挨着写
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
