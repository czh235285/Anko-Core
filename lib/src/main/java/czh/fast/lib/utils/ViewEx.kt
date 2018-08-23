package czh.fast.lib.utils

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
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