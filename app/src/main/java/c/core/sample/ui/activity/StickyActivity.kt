package c.core.sample.ui.activity

import androidx.recyclerview.widget.LinearLayoutManager
import c.core.adapter.dslAdapter
import c.core.adapter.dslItem
import c.core.sample.base.AnkoActivity
import c.core.sample.ui.layout.ItemUI
import c.core.sample.ui.layout.StickyActivityUI
import org.jetbrains.anko.setContentView

/**
 *  author : czh
 *  date : 2020-7-29
 *  description : 
 */
class StickyActivity : AnkoActivity() {
    val ui = StickyActivityUI()
    override fun ankoLayout() {
        ui.setContentView(this)
    }

    override fun afterInitView() {
        val data = mutableListOf<String>()
        repeat(100) {
            data.add(it.toString())
        }
        ui.rcv.layoutManager = LinearLayoutManager(this)
        ui.rcv.dslAdapter {
            replaceData(data.map {
                dslItem<ItemUI> { holder, position ->
                    tv.text = it
                }
            })
        }
    }
}
