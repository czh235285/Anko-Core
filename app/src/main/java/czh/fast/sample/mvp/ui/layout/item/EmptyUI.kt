package czh.fast.sample.mvp.ui.layout.item

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import org.jetbrains.anko.*

class EmptyUI : AnkoComponent<Context> {

    @SuppressLint("ResourceType")
    override fun createView(ui: AnkoContext<Context>) = with(ui) {
        relativeLayout {

            lparams(matchParent, matchParent)
            textView("空布局") {
                gravity = Gravity.CENTER
            }.lparams(matchParent, matchParent) {
                centerInParent()
            }

        }
    }
}

