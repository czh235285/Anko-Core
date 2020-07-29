package c.core.widget

import android.content.Context
import android.view.Gravity
import android.view.ViewManager
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import c.core.R
import c.core.ex.anko.horizontalLayout
import c.core.ex.px
import c.core.ex.text
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.imageView
import org.jetbrains.anko.space


inline fun ViewManager.commonItemView(
    theme: Int = 0,
    init: CommonItemView.() -> Unit
): CommonItemView {
    return ankoView({
        CommonItemView(it)
    }, theme, init)
}

class CommonItemView : FrameLayout {
    private lateinit var rightIcon: ImageView
    private lateinit var rightTextView: TextView
    private lateinit var leftTextView: TextView
    private lateinit var leftIcon: ImageView

    constructor(context: Context) : super(context)

    init {
        horizontalLayout {
            layoutParams = LayoutParams(-1, -1)
            horizontalLayout {
                space().lparams(52.px)
                leftIcon = imageView(R.mipmap.ic_info_details_education) {
                    scaleType = ImageView.ScaleType.FIT_XY
                }.lparams(42.px, 42.px) {
                    gravity = Gravity.CENTER_VERTICAL
                    rightMargin = 21.px
                }
                leftTextView = text(33, "#363636", "四川成都双流县四川成都双流县", isBOLD = true, maxLine = 1) {
                    gravity = Gravity.CENTER_VERTICAL
                }.lparams(-1, -1) {
                    rightMargin = 10.px
                }
            }.lparams(0, -1, 1f)
            horizontalLayout {

                rightTextView = text(33, "#363636", "四川成都双流县四川成都双流县", maxLine = 1) {
                    gravity = Gravity.CENTER_VERTICAL or Gravity.RIGHT
                }.lparams(0, -1, 1f) {
                    leftMargin = 10.px
                }

                rightIcon = imageView(R.mipmap.ic_arrow_right){
                    scaleType=ImageView.ScaleType.FIT_XY
                }.lparams(17.px, 25.px) {
                    gravity = Gravity.CENTER_VERTICAL
                    leftMargin = 21.px
                }
                space().lparams(52.px)


            }.lparams(0, -1, 1f)
        }
    }
}