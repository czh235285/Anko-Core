package czh.fast.sample.mvp.ui.activity

import android.content.Context
import android.widget.TextView
import czh.adapter.AnkoAdapter
import czh.adapter.holer.AnkoViewHolder
import czh.fast.sample.base.AnkoActivity
import czh.fast.sample.mvp.ui.layout.BehaviorActivityUI
import czh.fast.sample.utils.Text
import czh.fast.sample.utils.px
import org.jetbrains.anko.*

class BehaviorActivity : AnkoActivity() {
    val ui = BehaviorActivityUI()
    override fun ankoLayout() {
        ui.setContentView(this)
    }


    override fun afterInitView() {
        val list = mutableListOf<Int>()
        repeat(100) {
            list.add(it)
        }

        ui.rcv.adapter = object : AnkoAdapter<Int>(list) {
            override fun ankoLayout(viewType: Int): AnkoComponent<Context> {
                return TextUI()
            }

            override fun convert(holder: AnkoViewHolder, position: Int, item: Int?) {
                (holder.ui as TextUI).apply {
                    tv.text = item.toString()
                }
            }

        }
    }


    inner class TextUI : AnkoComponent<Context> {
        lateinit var tv: TextView

        override fun createView(ui: AnkoContext<Context>) = with(ui) {
            verticalLayout {
                tv = Text(28, "#333333").lparams {
                    leftMargin = 30.px
                    verticalMargin = 30.px
                }
            }
        }

    }
}