package c.core.widget

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.ViewManager
import androidx.appcompat.widget.AppCompatImageView
import org.jetbrains.anko.custom.ankoView


/**
 * 通用imageView
 */
fun ViewManager.simpleImageView(theme: Int = 0) = simpleImageView(theme) {
}

/**
 * 通用imageView
 */
inline fun ViewManager.simpleImageView(
    theme: Int = 0,
    init: SimpleImageView.() -> Unit
): SimpleImageView {
    return ankoView({
        SimpleImageView(it)
    }, theme, init)
}

class SimpleImageView : AppCompatImageView {


    private var isCoverSrc = false
    private var isCircle = false
    private var strokeWidth = 0
    private var strokeColor = Color.WHITE
    private var maskColor: Int? = null
    private var radius: Float = 0F
    private var topLeft: Float = Float.NaN
    private var topRight: Float = Float.NaN
    private var bottomLeft: Float = Float.NaN
    private var bottomRight: Float = Float.NaN

    private var ratio: Float = 0f


    private val strokeRadii: FloatArray?
        get() {
            if (isCircle) {
                return null
            }
            return floatArrayOf(
                topLeft.orRadius(),
                topLeft.orRadius(),

                topRight.orRadius(),
                topRight.orRadius(),

                bottomRight.orRadius(),
                bottomRight.orRadius(),

                bottomLeft.orRadius(),
                bottomLeft.orRadius()
            )
        }

    private val srcRadii: FloatArray?
        get() {
            if (isCircle) {
                return null
            }
            return floatArrayOf(
                topLeft.orRadius() - strokeWidth / 2.0f,
                topLeft.orRadius() - strokeWidth / 2.0f,

                topRight.orRadius() - strokeWidth / 2.0f,
                topRight.orRadius() - strokeWidth / 2.0f,

                bottomRight.orRadius() - strokeWidth / 2.0f,
                bottomRight.orRadius() - strokeWidth / 2.0f,

                bottomLeft.orRadius() - strokeWidth / 2.0f,
                bottomLeft.orRadius() - strokeWidth / 2.0f
            )
        }
    private val srcRectF: RectF?
        get() {
            if (isCircle) {
                radius = width.coerceAtMost(height) / 2.0f
                return RectF(
                    width / 2.0f - radius,
                    height / 2.0f - radius,
                    width / 2.0f + radius,
                    height / 2.0f + radius
                )
            }

            if (isCoverSrc) {
                return strokeRectF
            }
            return RectF(0f, 0f, width.toFloat(), height.toFloat())
        }


    private val strokeRectF: RectF?
        get() {
            if (isCircle) {
                return null
            }
            return RectF(
                strokeWidth / 2f,
                strokeWidth / 2f,
                width - strokeWidth / 2f,
                height - strokeWidth / 2f
            )
        }
    private val path = Path() // 用来裁剪图片的ptah
    private val srcPath = Path() // 图片区域大小的path
    private val xFerMode: Xfermode
        get() {
            return if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O_MR1) {
                PorterDuffXfermode(PorterDuff.Mode.DST_IN)
            } else {
                PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
            }
        }


    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )


    fun configs(init: Config.() -> Unit) {
        Config().also {
            it.init()
            isCoverSrc = it.isCoverSrc
            isCircle = it.isCircle
            strokeWidth = it.strokeWidth
            strokeColor = it.strokeColor
            maskColor = it.maskColor
            radius = it.radius
            topLeft = it.topLeft
            topRight = it.topRight
            bottomLeft = it.bottomLeft
            bottomRight = it.bottomRight
            ratio = it.ratio
            invalidate()
        }
    }

    private fun Float.orRadius(): Float =
        takeIf { it >= 0 } ?: radius

    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG)

    override fun onDraw(canvas: Canvas?) {

        // 使用图形混合模式来显示指定区域的图片
        canvas?.saveLayer(srcRectF, null, Canvas.ALL_SAVE_FLAG)
        if (!isCoverSrc) {
            val sx: Float = (width - 2 * strokeWidth).toFloat() / width
            val sy: Float = (height - 2 * strokeWidth).toFloat() / height
            // 缩小画布，使图片内容不被strokes覆盖
            canvas?.scale(sx, sy, width / 2.0f, height / 2.0f)
        }
        super.onDraw(canvas)
        paint.reset()
        path.reset()
        if (isCircle) {
            path.addCircle(width / 2.0f, height / 2.0f, radius, Path.Direction.CCW)
        } else {
            path.addRoundRect(srcRectF, srcRadii, Path.Direction.CCW)
        }
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL
        paint.xfermode = xFerMode
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O_MR1) {
            canvas?.drawPath(path, paint)
        } else {
            srcPath.reset()
            srcPath.addRect(srcRectF, Path.Direction.CCW)
            // 计算tempPath和path的差集
            srcPath.op(path, Path.Op.DIFFERENCE)
            canvas?.drawPath(srcPath, paint)
        }
        paint.xfermode = null
        // 绘制遮罩
        maskColor?.also {
            paint.color = it
            canvas?.drawPath(path, paint)
        }
        // 恢复画布
        canvas?.restore()


        // 绘制边框
        if (isCircle) {
            if (strokeWidth > 0) {
                initStrokePaint(strokeWidth, strokeColor)
                path.addCircle(
                    width / 2.0f,
                    height / 2.0f,
                    radius - strokeWidth / 2.0f,
                    Path.Direction.CCW
                )
                canvas?.drawPath(path, paint)
            }
        } else {
            if (strokeWidth > 0) {
                initStrokePaint(strokeWidth, strokeColor)
                path.addRoundRect(strokeRectF, strokeRadii, Path.Direction.CCW)
                canvas?.drawPath(path, paint)
            }
        }



    }

    private fun initStrokePaint(strokeWidth: Int, strokeColor: Int) {
        path.reset()
        paint.strokeWidth = strokeWidth.toFloat()
        paint.color = strokeColor
        paint.style = Paint.Style.STROKE
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //获取宽度的模式和尺寸
        var measuredWidth = widthMeasureSpec
        var measuredHeight = heightMeasureSpec
        var widthSize = MeasureSpec.getSize(measuredWidth)
        val widthMode = MeasureSpec.getMode(measuredWidth)
        //获取高度的模式和尺寸
        var heightSize = MeasureSpec.getSize(measuredHeight)
        val heightMode = MeasureSpec.getMode(measuredHeight)
        //宽确定，高不确定
        if (widthMode == MeasureSpec.EXACTLY && heightMode != MeasureSpec.EXACTLY && ratio != 0f) {
            heightSize = (widthSize / ratio + 0.5f).toInt() //根据宽度和比例计算高度
            measuredHeight = MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.EXACTLY)
        } else if (widthMode != MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY && ratio != 0f) {
            widthSize = (heightSize * ratio + 0.5f).toInt()
            measuredWidth = MeasureSpec.makeMeasureSpec(widthSize, MeasureSpec.EXACTLY)
        } else {
            super.onMeasure(measuredWidth, measuredHeight)
            return
        }
        setMeasuredDimension(measuredWidth, measuredHeight)
    }

    class Config {
        var isCoverSrc = false
        var isCircle = false
        var strokeWidth = 0
        var strokeColor = Color.WHITE
        var maskColor: Int? = null
        var radius: Float = 0F
        var topLeft: Float = Float.NaN
        var topRight: Float = Float.NaN
        var bottomLeft: Float = Float.NaN
        var bottomRight: Float = Float.NaN
        var ratio: Float = 0f
    }
}