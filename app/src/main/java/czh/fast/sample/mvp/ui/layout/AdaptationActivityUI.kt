package czh.fast.sample.mvp.ui.layout

import android.graphics.Color
import android.view.Gravity
import android.view.View
import czh.fast.sample.mvp.ui.activity.AdaptationActivity
import czh.fast.sample.utils.hpx
import czh.fast.sample.utils.px
import org.jetbrains.anko.*

class AdaptationActivityUI : AnkoComponent<AdaptationActivity> {
    override fun createView(ui: AnkoContext<AdaptationActivity>): View = with(ui) {
        relativeLayout {
            view {
                backgroundColor = Color.parseColor("#ff0000")
            }.lparams(250.px, 1334.hpx / 3)
            textView {
                gravity = Gravity.CENTER
                textColor = Color.parseColor("#ffffff")
                text = "简单的布局宽高适配,没用权重，数字宽高数字直接写，任何分辨率设备保证均分"
                backgroundColor = Color.parseColor("#0000ff")
            }.lparams(250.px, 1334.hpx / 3) {
                leftMargin = 250.px
                topMargin = 1334.hpx / 3
            }
            view {
                backgroundColor = Color.parseColor("#ffff00")
            }.lparams(250.px, 1334.hpx / 3) {
                leftMargin = 500.px
                topMargin = 1334.hpx * 2 / 3
            }


        }
    }
}