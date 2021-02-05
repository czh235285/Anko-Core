package c.core.sample.ui.activity

import android.animation.ValueAnimator
import android.widget.LinearLayout
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import c.core.ex.*
import c.core.sample.base.AnkoActivity
import c.core.sample.ui.layout.EditAniTestActivityUI
import c.core.utils.throttleClick
import kotlinx.coroutines.delay
import org.jetbrains.anko.horizontalMargin
import org.jetbrains.anko.setContentView

/**
 *  author : czh
 *  date : 2020-8-6
 *  description : 
 */
class EditAniTestActivity : AnkoActivity() {
    private lateinit var valueAnimator: ValueAnimator

    val ui = EditAniTestActivityUI()
    override fun ankoLayout() {
        ui.setContentView(this)
    }

    fun startAni() {
        lifecycleScope.launchWhenCreated {
            var a = 1
            do {
                a++

                ui.bar.layoutParams = LinearLayout.LayoutParams(-1, -2).apply {
                    topMargin = ((-88).px * a) / 100
                }

                ui.bar.tvTitle.alpha = 1f - (a / 100)

                ui.et.layoutParams =
                    LinearLayout.LayoutParams(690.px - (80.px * a / 100), -2).apply {
                        horizontalMargin = 30.px
                        bottomMargin = 10.px
                    }
                delay(2)
            } while (a < 100)
        }
    }

    fun stopAni() {
        lifecycleScope.launchWhenCreated {
            var a = 100
            do {
                a--

                ui.bar.layoutParams = LinearLayout.LayoutParams(-1, -2).apply {
                    topMargin = ((-88).px * a) / 100
                }
                ui.bar.tvTitle.alpha = 1f - (a / 100)
                ui.et.layoutParams =
                    LinearLayout.LayoutParams(690.px - (80.px * a / 100), -2).apply {
                        horizontalMargin = 30.px
                        bottomMargin = 10.px
                    }
                delay(2)
            } while (a > 0)
        }
    }

    override fun afterInitView() {
        with(ui) {

            et.setOnFocusChangeListener { v, hasFocus ->
                llcontent.isInvisible = !hasFocus
                if (hasFocus) {
                    startAni()
                } else {
                    stopAni()
                }
            }
            et.throttleClick {
                et.showSoftInput()
            }
            etCancel.throttleClick {
                et.hideSoftInput()
            }
        }
    }
}
