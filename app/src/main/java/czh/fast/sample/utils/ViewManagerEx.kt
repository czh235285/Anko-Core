package czh.fast.sample.utils

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import androidx.annotation.Size
import android.text.TextUtils
import android.util.TypedValue
import android.view.ViewGroup
import android.view.ViewManager
import android.widget.TextView
import com.facebook.drawee.generic.RoundingParams
import com.facebook.drawee.view.SimpleDraweeView
import com.youth.banner.Banner
import czh.fast.sample.widget.AnkoToolBar
import czh.library.LikeView
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView

/**
 * LikeView
 */
inline fun ViewManager.likeView(theme: Int = 0) = likeView(theme) {}


/**
 * simpleDraweeView
 */
inline fun ViewManager.simpleDraweeView(theme: Int = 0) = simpleDraweeView(theme) {}

/**
 * simpleDraweeView
 */
inline fun ViewManager.simpleDraweeView(theme: Int = 0, init: SimpleDraweeView.() -> Unit): SimpleDraweeView {
    return ankoView({ SimpleDraweeView(it) }, theme, init)
}


/**
 * circleImageView
 */
inline fun ViewManager.circleImageView() = circleImageView {
}

/**
 * circleImageView
 */
inline fun ViewManager.circleImageView(init: SimpleDraweeView.() -> Unit): SimpleDraweeView {
    return simpleDraweeView(0, init).apply {
        hierarchy.apply {
            val roundingParams = RoundingParams()
            roundingParams.roundAsCircle = true
            hierarchy.roundingParams = roundingParams
        }
    }
}


/**
 * roundImageView
 */
inline fun ViewManager.roundImageView(corners: Float) = roundImageView(corners) {
}

/**
 * roundImageView
 */
inline fun ViewManager.roundImageView(corners: Float, init: SimpleDraweeView.() -> Unit): SimpleDraweeView {
    return simpleDraweeView(0, init).apply {
        hierarchy.apply {
            val roundingParams = RoundingParams()
            roundingParams.setCornersRadius(corners)
            hierarchy.roundingParams = roundingParams
        }
    }
}


/**
 * roundImageView
 */
inline fun ViewManager.roundImageView(topLeft: Float, topRight: Float, bottomRight: Float, bottomLeft: Float) = roundImageView(topLeft, topRight, bottomRight, bottomLeft) {
}

/**
 * roundImageView
 */
inline fun ViewManager.roundImageView(topLeft: Float, topRight: Float, bottomRight: Float, bottomLeft: Float, init: SimpleDraweeView.() -> Unit): SimpleDraweeView {
    return simpleDraweeView(0, init).apply {
        hierarchy.apply {
            val roundingParams = RoundingParams()
            roundingParams.setCornersRadii(topLeft, topRight, bottomRight, bottomLeft)
            hierarchy.roundingParams = roundingParams
        }
    }
}
/**
 * LikeView
 */
inline fun ViewManager.likeView(theme: Int = 0, init: LikeView.() -> Unit): LikeView {
    return ankoView({ LikeView(it) }, theme, init)
}

inline fun ViewManager.banners(theme: Int = 0) = banners(theme) {}

inline fun ViewManager.banners(theme: Int = 0, init: Banner.() -> Unit): Banner {
    return ankoView({ Banner(it) }, theme, init)
}

inline fun ViewManager.ankoToolBar(theme: Int = 0, init: AnkoToolBar.() -> Unit): AnkoToolBar {
    return ankoView({
        AnkoToolBar(it).apply {
            layoutParams = ViewGroup.LayoutParams(matchParent, dip(48))
        }
    }, theme, init)
}


fun ViewManager.text(textsize: Int=24, @Size(min = 1) colorString: String = "#333333", content: String? = null, isSingleLine: Boolean = false, isBOLD: Boolean = false, maxLine: Int? = null) = Text(textsize, colorString, content, isSingleLine, isBOLD, maxLine) {

}
fun ViewManager.Text(textsize: Int, @Size(min = 1) colorString: String = "#333333", content: String? = null, isSingleLine: Boolean = false, isBOLD: Boolean = false, maxLine: Int? = null, init: TextView.() -> Unit): TextView {
    return ankoView({
        TextView(it).apply {
            content?.let {
                text = it
            }
            setTextSize(TypedValue.COMPLEX_UNIT_PX, textsize.pxf)
            textColor = try {
                Color.parseColor(colorString)
            } catch (e: Throwable) {
                Color.parseColor("#333333")
            }

            if (isSingleLine) {
                singleLine = true
                ellipsize = TextUtils.TruncateAt.END
            }

            maxLine?.let {
                maxLines = it
                ellipsize = TextUtils.TruncateAt.END
            }
            if (isBOLD) {
                typeface = Typeface.defaultFromStyle(Typeface.BOLD)
            }


        }
    }, 0, init)
}

@SuppressLint("WrongConstant")
val unSelectedDrawable = GradientDrawable().apply {
    setColor(Color.parseColor("#FFFFFF"))
    gradientType = GradientDrawable.OVAL
    cornerRadius = 30f
}

@SuppressLint("WrongConstant")
val selectedDrawable = GradientDrawable().apply {
    setColor(Color.parseColor("#FF444A"))
    gradientType = GradientDrawable.OVAL
    cornerRadius = 30f
}