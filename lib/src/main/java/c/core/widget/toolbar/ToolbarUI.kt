package c.core.widget.toolbar

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import c.core.R
import c.core.ex.px
import c.core.ex.text

import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.*


class ToolbarUI : AnkoComponent<Context> {
    lateinit var ivBack: ImageView

    lateinit var tvTitle: TextView

    lateinit var tvRight: TextView

    lateinit var ivRight: ImageView

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        return relativeLayout {
            lparams(-1, 88.px)
            ivBack = imageView(R.mipmap.icon_fh_black) {
                leftPadding = 30.px
                scaleType = ImageView.ScaleType.CENTER_INSIDE
                setOnClickListener {
                    val ctx = context
                    if (ctx is Activity) {
                        ctx.onBackPressed()
                    }
                }
            }.lparams(78.px, matchParent) {
                centerVertically()

            }
            tvTitle = text(36, "#333333") {

            }.lparams {
                centerInParent()
            }

            tvRight = textView {
                gravity = Gravity.CENTER
                visibility = View.GONE
                textSize = 14f
                textColor = Color.parseColor("#409EFF")
                rightPadding = 30.px
            }.lparams(wrapContent, matchParent) {
                alignParentRight()
                centerVertically()
            }
            ivRight = imageView {
                visibility = View.GONE
                leftPadding = 16.px
                rightPadding = 16.px
            }.lparams(wrapContent, matchParent) {
                alignParentRight()
                centerVertically()
            }
        }
    }

}
