package c.core.utils

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.content.res.ColorStateList
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import java.lang.Exception

val String.color: Int
    get() = try {
        Color.parseColor(this)
    } catch (e: Exception) {
        Color.parseColor("#ff0000")
    }

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

fun Context.resColor(@ColorRes id: Int): Int {
    return ContextCompat.getColor(this, id)
}