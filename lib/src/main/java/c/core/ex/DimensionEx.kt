package c.core.ex


inline val Int.dp: Int get() = this.dpf.toInt()
inline val Int.dpf: Float get() = this * appCtx.resources.displayMetrics.density


inline val Int.px: Int get() = (wProportion * this).toInt()
inline val Int.pxf: Float get() = (wProportion * this)
inline val Int.hpx: Int get() = (hProportion * this).toInt()
inline val Int.hpxf: Float get() = (hProportion * this)

val screenWidth by lazy {
    appCtx.resources.displayMetrics.widthPixels
}
val screenHeight by lazy {
    appCtx.resources.displayMetrics.heightPixels
}

val wProportion: Float
    get() = screenWidth.toFloat() / DimensionEx.designWidth

val hProportion: Float
    get() = screenHeight.toFloat() / DimensionEx.designHeight


// 状态栏高度
val statusBarHeight: Int
    get() {
        val resourceId: Int =
            appCtx.resources.getIdentifier("status_bar_height", "dimen", "android")
        return appCtx.resources.getDimensionPixelSize(resourceId)
    }


object DimensionEx {
    var designWidth: Int = 750
    var designHeight: Int = 1334

    fun init(designWidth: Int, designHeight: Int) {
        this.designWidth = designWidth
        this.designHeight = designHeight
    }
}
