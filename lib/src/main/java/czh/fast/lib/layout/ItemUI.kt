package czh.fast.lib.layout

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import org.jetbrains.anko.*

class ItemUI : AnkoComponent<Context> {
    lateinit var leftTextView: TextView
    lateinit var rightTextView: TextView
    lateinit var itemLayout: RelativeLayout
    override fun createView(ui: AnkoContext<Context>): View = with(ui) {

        frameLayout {
            itemLayout = relativeLayout {

                leftTextView = textView {
                    gravity = Gravity.CENTER
                }.lparams {
                    centerVertically()
                }
                rightTextView = textView {
                    gravity = Gravity.CENTER
                }.lparams {
                    centerVertically()
                    alignParentRight()
                }
            }
        }
    }
}