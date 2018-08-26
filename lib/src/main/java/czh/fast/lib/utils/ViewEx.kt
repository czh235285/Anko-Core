package czh.fast.lib.utils

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import org.jetbrains.anko.backgroundDrawable


fun View.setShape(solidColor: String? = null, radius: Float? = null, strokeWidth: Int? = null, strokeColor: String = "#cccccc", radiusArray: FloatArray? = null) {
    this.backgroundDrawable = GradientDrawable().apply {
        gradientType = GradientDrawable.RECTANGLE
        solidColor?.let {
            setColor(Color.parseColor(it))
        }
        radius?.let {
            cornerRadius = it
        }
        radiusArray?.let {
            cornerRadii = it
        }
        strokeWidth?.let {
            setStroke(it, Color.parseColor(strokeColor))
        }

    }
}

fun LinearLayout.centerInParent() {
    gravity = Gravity.CENTER
}

fun LinearLayout.centerHorizontally() {
    gravity = Gravity.CENTER_HORIZONTAL
}

fun LinearLayout.centerVertically() {
    gravity = Gravity.CENTER_VERTICAL
}

