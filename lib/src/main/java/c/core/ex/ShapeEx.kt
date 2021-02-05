package c.core.ex

import android.annotation.TargetApi
import android.graphics.drawable.GradientDrawable
import android.view.View
import org.jetbrains.anko.internals.AnkoInternals.NO_GETTER

inline fun View.buildShape(fill: GradientDrawable.() -> Unit) {
    background = GradientDrawable().also {
        it.gradientType = GradientDrawable.LINEAR_GRADIENT
        it.fill()
    }
}

inline fun shapeDrawable(fill: GradientDrawable.() -> Unit): GradientDrawable =
    GradientDrawable().also {
        it.gradientType = GradientDrawable.LINEAR_GRADIENT
        it.fill()
    }

inline fun View.commonShape(
    solidColor: Int,
    radius: Float? = null,
    strokeColor: Int? = null,
    strokeWidth: Int? = null,
    fill: GradientDrawable.() -> Unit? = {
    }
) = buildShape {
    shapeEnum = Shape.RECTANGLE
    shapeSolidColor = solidColor
    radius?.let { cornerRadius = it }
    if (strokeWidth != null && strokeColor != null) {
        setStroke(strokeWidth, strokeColor)
    }
    this.fill()
}

enum class Shape {
    RECTANGLE,
    OVAL,
    LINE,
    RING,
}

typealias ShapeInt = Int

fun toInt(s: Shape): ShapeInt =
    when (s) {
        Shape.RECTANGLE ->
            GradientDrawable.RECTANGLE

        Shape.OVAL ->
            GradientDrawable.OVAL

        Shape.LINE ->
            GradientDrawable.LINE

        Shape.RING ->
            GradientDrawable.RING
    }

fun fromInt(s: ShapeInt): Shape? =
    when (s) {
        GradientDrawable.RECTANGLE ->
            Shape.RECTANGLE

        GradientDrawable.OVAL ->
            Shape.OVAL

        GradientDrawable.LINE ->
            Shape.LINE

        GradientDrawable.RING ->
            Shape.RING

        else ->
            null
    }

var GradientDrawable.shapeEnum: Shape
    set(value) {
        shape = toInt(value)
    }
    @TargetApi(24) get() = fromInt(shape) ?: error("Illegal shape int $shape")

var GradientDrawable.shapeSolidColor: Int
    set(value) = setColor(value)
    @Deprecated(message = NO_GETTER, level = DeprecationLevel.HIDDEN) get() = error(NO_GETTER)

class Gradient {
    var startColor: Int = -1
    var endColor: Int = -1
    var orientation: GradientDrawable.Orientation = GradientDrawable.Orientation.LEFT_RIGHT
}

/**
 * 渐变色
 */
fun GradientDrawable.shapeGradient(fill: Gradient.() -> Unit) {
    Gradient().also {
        it.fill()
        orientation = it.orientation
        colors = intArrayOf(it.startColor, it.endColor)
    }
}

class Stroke {
    var width: Int = -1
    var color: Int = -1
    var dashWidth: Float = 0F
    var dashGap: Float = 0F
}

/**
 * 边框
 */
fun GradientDrawable.shapeStroke(fill: Stroke.() -> Unit) {
    Stroke().also {
        it.fill()
        setStroke(it.width, it.color, it.dashWidth, it.dashGap)
    }
}


class Corners {
    var radius: Float = 0F

    var topLeft: Float = Float.NaN
    var topRight: Float = Float.NaN
    var bottomLeft: Float = Float.NaN
    var bottomRight: Float = Float.NaN

    internal fun Float.orRadius(): Float =
        takeIf { it >= 0 } ?: radius
}

fun Corners.render(): FloatArray =
    floatArrayOf(
        topLeft.orRadius(),
        topLeft.orRadius(),

        topRight.orRadius(),
        topRight.orRadius(),

        bottomRight.orRadius(),
        bottomRight.orRadius(),

        bottomLeft.orRadius(),
        bottomLeft.orRadius()
    )

inline fun GradientDrawable.shapeRadius(fill: Corners.() -> Unit) {
    Corners().also {
        it.fill()
        cornerRadii = it.render()
    }
}
