package czh.fast.lib.layout

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import czh.fast.lib.R
import org.jetbrains.anko.*

class ProgressBarUI : AnkoComponent<Context> {
    lateinit var iv_blue: ImageView

    lateinit var iv_yellow: ImageView

    lateinit var iv_red: ImageView

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        frameLayout {
            frameLayout {
                lparams(400, 400)
                backgroundColor = Color.parseColor("#00ffffff")
                iv_blue = imageView(R.mipmap.dot_blue).lparams {
                    gravity = Gravity.CENTER
                }
                iv_yellow = imageView(R.mipmap.dot_yellow).lparams {
                    gravity = Gravity.CENTER
                }
                iv_red = imageView(R.mipmap.dot_red).lparams {
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