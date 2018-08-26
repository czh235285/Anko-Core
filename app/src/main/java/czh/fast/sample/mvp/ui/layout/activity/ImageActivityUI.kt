package czh.fast.sample.mvp.ui.layout.activity

import android.view.View
import android.widget.LinearLayout
import com.facebook.drawee.view.SimpleDraweeView
import czh.fast.lib.utils.anko.circleImageView
import czh.fast.lib.utils.anko.roundImageView
import czh.fast.lib.utils.anko.simpleDraweeView
import czh.fast.lib.utils.centerHorizontally
import czh.fast.lib.utils.load
import czh.fast.sample.mvp.ui.activity.ImageActivity
import czh.fast.sample.utils.ankoToolBar

import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.*


class ImageActivityUI : AnkoComponent<ImageActivity> {
    val url = "https://cdn.duitang.com/uploads/item/201412/03/20141203090917_rwm5J.thumb.700_0.jpeg"
    override fun createView(ui: AnkoContext<ImageActivity>): View = with(ui) {
        return verticalLayout {
            centerHorizontally()
            ankoToolBar {
                title = "图片"
            }
            simpleDraweeView()
            circleImageView()
            roundImageView(dip(10).toFloat())
            roundImageView(dip(10).toFloat(), dip(20).toFloat(), dip(30).toFloat(), dip(40).toFloat())

        }.applyRecursively {
            if (it is SimpleDraweeView) {
                it.load(url)
                it.layoutParams = LinearLayout.LayoutParams(300, 300).apply {
                    topMargin = dip(30)
                }
            }
        }
    }

}
