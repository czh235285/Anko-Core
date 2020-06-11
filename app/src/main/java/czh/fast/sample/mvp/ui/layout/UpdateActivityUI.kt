package czh.fast.sample.mvp.ui.layout

import android.view.Gravity
import android.view.View
import czh.fast.lib.utils.setShape
import czh.fast.sample.mvp.ui.activity.UpdateActivity
import czh.fast.sample.utils.Text
import czh.fast.sample.utils.ankoToolBar
import czh.fast.sample.utils.px
import czh.fast.sample.utils.pxf
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.verticalLayout

class UpdateActivityUI : AnkoComponent<UpdateActivity> {
    override fun createView(ui: AnkoContext<UpdateActivity>): View = with(ui) {
        verticalLayout {
            ankoToolBar { title = "版本更新" }


            Text(30, "#ffffff", "通知栏更新") {
                gravity=Gravity.CENTER
                setOnClickListener {
                    owner.update()
                }
                setShape("#FF4403", 40.pxf)
            }.lparams(300.px, 80.px) {
                topMargin=100.px
                gravity = Gravity.CENTER
            }
            Text(30, "#ffffff", "调用系统浏览器更新") {
                gravity=Gravity.CENTER
                setOnClickListener {
                    owner.openBrowserUpdate()
                }
                setShape("#FF4403", 40.pxf)
            }.lparams(300.px, 80.px) {
                topMargin=50.px
                gravity = Gravity.CENTER
            }
        }
    }
}