package czh.fast.sample.widget


import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewManager
import android.widget.FrameLayout
import org.jetbrains.anko.relativeLayout
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.view.animation.AnimationSet
import czh.fast.sample.R
import czh.fast.sample.utils.px
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.centerInParent
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.view


inline fun ViewManager.loadingView(
        theme: Int = 0,
        init: LoadingView.() -> Unit
): LoadingView {
    return ankoView({
        LoadingView(it)
    }, theme, init)
}


class LoadingView : FrameLayout {
    private lateinit var animationSet: AnimationSet
    lateinit var loadingView: View

    init {
        relativeLayout {
            lparams(200.px, 200.px)
            loadingView = view {
                backgroundResource = R.mipmap.loading
            }.lparams(72.px, 72.px) {
                centerInParent()
            }
        }
        loadIng()
    }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
            context,
            attrs,
            defStyleAttr
    )


    //加载动画
    private fun loadIng() {
        animationSet = AnimationSet(true)
        RotateAnimation(
                0f,
                +359f,
                RotateAnimation.RELATIVE_TO_SELF,
                0.5f,
                RotateAnimation.RELATIVE_TO_SELF,
                0.5f
        ).let {
            it.repeatCount = -1
            it.startOffset = 0
            it.duration = 1000
            animationSet.interpolator = LinearInterpolator()
            animationSet.addAnimation(it)
        }
    }

    @SuppressLint("NewApi")
    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        if (visibility == View.VISIBLE) {
            loadingView.startAnimation(animationSet)
        } else {
            loadingView.clearAnimation()
        }
    }

    override fun onWindowVisibilityChanged(visibility: Int) {
        super.onWindowVisibilityChanged(visibility)
        if (visibility == View.VISIBLE) {
            loadingView.startAnimation(animationSet)
        } else {
            loadingView.clearAnimation()
        }
    }
}