package czh.fast.lib.utils

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.content.res.ColorStateList
import androidx.core.graphics.drawable.DrawableCompat


fun Int.changeAlpha(percentage: Float): Int {
    val red = Color.red(this)
    val green = Color.green(this)
    val blue = Color.blue(this)
    val alpha = (Color.alpha(this) * percentage).toInt()
    return Color.argb(alpha, red, green, blue)
}

fun Drawable.tint(color: Int): Drawable {
    return DrawableCompat.wrap(this).also {
        val colors = ColorStateList.valueOf(color)
        DrawableCompat.setTintList(it, colors)
    }
}