package czh.fast.sample.widget

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import czh.fast.sample.R

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
            ivBack = imageView(R.mipmap.btn_back) {
                scaleType = ImageView.ScaleType.CENTER_INSIDE
                setOnClickListener {
                    val ctx = context
                    if (ctx is Activity) {
                        ctx.finish()
                    }
                }
            }.lparams(dip(48), matchParent) {
                centerVertically()
            }
            tvTitle = textView {
                textSize = 20f
                textColor = Color.parseColor("#333333")
            }.lparams {
                centerInParent()
            }

            tvRight = textView {
                visibility = View.GONE
                textSize = 14f
                textColor = Color.parseColor("#333333")
                rightPadding = dip(16)
            }.lparams(wrapContent, matchParent) {
                alignParentRight()
                centerVertically()
            }
            ivRight = imageView {
                visibility = View.GONE
                leftPadding = dip(16)
                rightPadding = dip(16)
            }.lparams(wrapContent, matchParent) {
                alignParentRight()
                centerVertically()
            }
        }
    }

}
