package c.core.ex

import android.graphics.Color
import android.graphics.Typeface
import android.text.TextUtils
import android.util.TypedValue
import android.view.ViewGroup
import android.view.ViewManager
import android.widget.TextView
import androidx.annotation.Size
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.textColor

fun ViewManager.text(
    textSize: Int,
    @Size(min = 7) colorString: String = "#333333",
    content: String? = null,
    isBOLD: Boolean = false,
    maxLine: Int? = null
) = text(textSize, colorString, content, isBOLD, maxLine) {

}


inline fun ViewManager.text(
    textSize: Int,
    @Size(min = 7) colorString: String = "#333333",
    content: String? = null,
    isBOLD: Boolean = false,
    maxLine: Int? = null,
    init: TextView.() -> Unit
): TextView {
    return ankoView({
        TextView(it).apply {
            content?.let {
                text = content
            }
            setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize.pxf)
            textColor = try {
                Color.parseColor(colorString)
            } catch (e: Throwable) {
                Color.parseColor("#333333")
            }
            maxLine?.let {
                maxLines = maxLine
                ellipsize = TextUtils.TruncateAt.END
            }
            if (isBOLD) {
                typeface = Typeface.defaultFromStyle(Typeface.BOLD)
            }


            if (layoutParams == null) {
                layoutParams = ViewGroup.LayoutParams(-2, -2)
            }
        }
    }, 0, init)
}