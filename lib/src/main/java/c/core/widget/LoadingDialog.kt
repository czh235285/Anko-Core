package c.core.widget

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import c.core.R
import c.core.layout.ProgressBarUI
import org.jetbrains.anko.AnkoContext
import java.util.ArrayList

class LoadingDialog(context: Context) : Dialog(context, R.style.YgDialog) {

    val ui = ProgressBarUI()

    /**
     * 存放三个小球的集合
     */
    private val views = ArrayList<ImageView>()

    /**
     * 同时播放动画的对象
     */
    private var animatorSet: AnimatorSet? = null

    override fun show() {
        super.show()
        startAnimator()
    }

    override fun dismiss() {
        super.dismiss()
        animatorSet?.cancel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ui.createView(AnkoContext.create(this.context)))

        ui.apply {
            views.add(ivYellow)
            views.add(ivRed)
            views.add(ivBlue)
        }
    }

    private fun startAnimator() {
        /**动画组合->让左右同时执行 */
        animatorSet = AnimatorSet()
        animatorSet?.play(startAnimator1())?.with(startAnimator2())?.with(startAnimator3())
        animatorSet?.interpolator = LinearInterpolator()
        animatorSet?.start()
    }

    private fun startAnimator1(): ObjectAnimator {
        /**对象的不同属性组合 */
        val objectAnimatorTranslation = PropertyValuesHolder.ofFloat(
            "translationX",
            -50f,
            -100f,
            -50f,
            0f,
            50f,
            100f,
            50f,
            0f,
            -50f
        )
        val objectAnimatorScale =
            PropertyValuesHolder.ofFloat("scaleX", 0.5f, 1f, 1.5f, 1f, 0.5f, 1f, 1.5f, 1f, 0.5f)
        val objectAnimatorScaleY =
            PropertyValuesHolder.ofFloat("scaleY", 0.5f, 1f, 1.5f, 1f, 0.5f, 1f, 1.5f, 1f, 0.5f)

        /**同时操作对象的两个属性动画 */
        val objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
            views[0],
            objectAnimatorTranslation,
            objectAnimatorScale,
            objectAnimatorScaleY
        )
        objectAnimator.repeatCount = -1
        objectAnimator.interpolator = LinearInterpolator()
        objectAnimator.duration = 2000
        objectAnimator.start()
        return objectAnimator
    }

    private fun startAnimator2(): ObjectAnimator {
        /**对象的不同属性组合 */
        val objectAnimatorTranslation = PropertyValuesHolder.ofFloat(
            "translationX",
            0f,
            50f,
            100f,
            50f,
            0f,
            -50f,
            -100f,
            -50f,
            0f
        )
        val objectAnimatorScale =
            PropertyValuesHolder.ofFloat("scaleX", 1f, 0.5f, 1f, 1.5f, 1f, 0.5f, 1f, 1.5f, 1f)
        val objectAnimatorScaleY =
            PropertyValuesHolder.ofFloat("scaleY", 1f, 0.5f, 1f, 1.5f, 1f, 0.5f, 1f, 1.5f, 1f)

        /**同时操作对象的两个属性动画 */
        val objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
            views[1],
            objectAnimatorTranslation,
            objectAnimatorScale,
            objectAnimatorScaleY
        )
        objectAnimator.repeatCount = -1
        objectAnimator.interpolator = LinearInterpolator()
        objectAnimator.duration = 2000
        objectAnimator.start()
        return objectAnimator
    }

    private fun startAnimator3(): ObjectAnimator {
        /**对象的不同属性组合 */
        val objectAnimatorTranslation = PropertyValuesHolder.ofFloat(
            "translationX",
            50f,
            0f,
            -50f,
            -100f,
            -50f,
            0f,
            50f,
            100f,
            50f
        )
        val objectAnimatorScale =
            PropertyValuesHolder.ofFloat("scaleX", 1.5f, 1f, 0.5f, 1f, 1.5f, 1f, 0.5f, 1f, 1.5f)
        val objectAnimatorScaleY =
            PropertyValuesHolder.ofFloat("scaleY", 1.5f, 1f, 0.5f, 1f, 1.5f, 1f, 0.5f, 1f, 1.5f)

        /**同时操作对象的两个属性动画 */
        val objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
            views[2],
            objectAnimatorTranslation,
            objectAnimatorScale,
            objectAnimatorScaleY
        )
        objectAnimator.repeatCount = -1
        objectAnimator.interpolator = LinearInterpolator()
        objectAnimator.duration = 2000
        objectAnimator.start()
        return objectAnimator
    }
}
