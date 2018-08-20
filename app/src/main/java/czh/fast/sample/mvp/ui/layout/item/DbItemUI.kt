package czh.fast.sample.mvp.ui.layout.item

import android.content.Context
import android.graphics.Color
import android.widget.LinearLayout
import android.widget.TextView
import org.jetbrains.anko.*

class DbItemUI : AnkoComponent<Context> {
    lateinit var tvName: TextView
    lateinit var tvAge: TextView

    override fun createView(ui: AnkoContext<Context>) = with(ui) {
        linearLayout {
            lparams(matchParent, wrapContent)
            orientation = LinearLayout.HORIZONTAL
            tvName = textView("姓名").lparams(0, wrapContent) {
                weight = 1f
            }

            tvAge = textView("性别").lparams(0, wrapContent) {
                weight = 1f
            }

        }.apply {
            applyRecursively {
                when (it) {
                    is TextView -> {
                        it.apply {
                            textSize = 14f
                            textColor = Color.parseColor("#333333")
                            setPadding(20, 20, 20, 20)
                        }
                    }
                }
            }
        }
    }
}

