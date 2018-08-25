package czh.fast.sample.mvp.ui.layout.activity

import android.view.Gravity
import android.view.View
import czh.fast.lib.utils.anko.circleImageView
import czh.fast.lib.utils.anko.roundImageView
import czh.fast.lib.utils.anko.simpleDraweeView
import czh.fast.lib.utils.load
import czh.fast.lib.utils.loadWrapHeight
import czh.fast.sample.mvp.ui.activity.ImageActivity
import czh.fast.sample.utils.ankoToolBar

import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.*


class ImageActivityUI : AnkoComponent<ImageActivity> {
    val url = "https://cdn.duitang.com/uploads/item/201412/03/20141203090917_rwm5J.thumb.700_0.jpeg"
    override fun createView(ui: AnkoContext<ImageActivity>): View = with(ui) {
        return verticalLayout {
            gravity = Gravity.CENTER_HORIZONTAL

            ankoToolBar {
                title = "图片"
            }
            simpleDraweeView {
                loadWrapHeight(url)
            }.lparams(300) {
                topMargin = dip(30)
            }

            circleImageView {
                load(url)
            }.lparams(300, 300) {
                topMargin = dip(30)
            }

            roundImageView(dip(10).toFloat()) {
                load(url)
            }.lparams(300, 300) {
                topMargin = dip(30)
            }

            roundImageView(dip(10).toFloat(), dip(20).toFloat(), dip(30).toFloat(), dip(40).toFloat()) {
                load(url)
            }.lparams(300, 300) {
                topMargin = dip(30)
            }

        }
    }

}
