package c.core.ex

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager


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
    get() = screenWidth.toFloat() / designWidth

val hProportion: Float
    get() = screenHeight.toFloat() / designHeight

val appInfo: ApplicationInfo
    get() {
        return appCtx.packageManager.getApplicationInfo(
            appCtx.packageName,
            PackageManager.GET_META_DATA
        )
    }

val designWidth: Int
    get() = appInfo.metaData.getInt("ui_design_width")
val designHeight: Int
    get() = appInfo.metaData.getInt("ui_design_height")


// 状态栏高度
val statusBarHeight: Int
    get() {
        val resourceId: Int =
            appCtx.resources.getIdentifier("status_bar_height", "dimen", "android")
        return appCtx.resources.getDimensionPixelSize(resourceId)
    }
