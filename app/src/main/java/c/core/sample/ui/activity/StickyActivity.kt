package c.core.sample.ui.activity

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import c.core.adapter.AnkoAdapter
import c.core.adapter.holer.AnkoViewHolder
import c.core.sample.base.AnkoActivity
import c.core.sample.ui.layout.StickyActivityUI
import c.core.sample.ui.layout.ItemUI
import org.jetbrains.anko.AnkoComponent
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
        ui.rcv.adapter = object : AnkoAdapter<String>(data) {
            override fun ankoLayout(viewType: Int): AnkoComponent<Context> {
                return ItemUI()
            }

            override fun convert(holder: AnkoViewHolder, position: Int, item: String?) {
                holder.getAnKoUi<ItemUI>()?.apply {
                    tv.text = item
                }
            }

        }
    }
}
