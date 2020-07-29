package c.core.layout

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import c.core.R
import org.jetbrains.anko.*

class ProgressBarUI : AnkoComponent<Context> {
    lateinit var ivBlue: ImageView

    lateinit var ivYellow: ImageView

    lateinit var ivRed: ImageView

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        frameLayout {
            frameLayout {
                backgroundColorResource = android.R.color.transparent
                lparams(400, 400)

                ivBlue = imageView(R.mipmap.dot_blue).lparams {
                    gravity = Gravity.CENTER
                }
                ivYellow = imageView(R.mipmap.dot_yellow).lparams {
                    gravity = Gravity.CENTER
                }
                ivRed = imageView(R.mipmap.dot_red).lparams {
                    gravity = Gravity.CENTER
                }
            }.applyRecursively {
                if (it is ImageView) {
                    it.scaleType = ImageView.ScaleType.MATRIX
                }
            }
        }

    }

}