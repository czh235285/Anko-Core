package czh.fast.lib.utils

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import czh.fast.lib.R

fun Context.getDrawableRes(@DrawableRes id: Int): Drawable {
    return ContextCompat.getDrawable(this, id)!!
}

fun Context.getColorRes(@ColorRes id: Int): Int {
    return ContextCompat.getColor(this, id)
}

fun Context.inflater(resource: Int): View {
    return LayoutInflater.from(this).inflate(resource, null)
}


fun Context.inflater(resource: Int, root: ViewGroup, attachToRoot: Boolean): View {
    return LayoutInflater.from(this).inflate(resource, root, attachToRoot)
}

/**
 * 获取屏幕宽度
 *
 * @return 屏幕宽度
 */
fun Context.screenWidth(): Int {
    return resources.displayMetrics.widthPixels
}

/**
 * 获取屏幕高度
 *
 * @return 屏幕高度
 */
fun Context.screenHeight(): Int {
    return resources.displayMetrics.heightPixels
}

/**
 * 获取状态栏高度
 *
 * @return 状态栏高度（px）
 */
fun Context.statusBarHeight(): Int {
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")

    var statusBarHeight = 0
    if (resourceId > 0) {
        statusBarHeight = resources.getDimensionPixelSize(resourceId)
    }

    return statusBarHeight
}

/**
 * 获取Version code
 *
 * @return version code
 */
fun Context.versionCode(): Int {
    return packageManager.getPackageInfo(packageName, 0).versionCode
}

/**
 * 获取Version name
 *
 * @return version name
 */
fun Context.versionName(): String {
    return packageManager.getPackageInfo(packageName, 0).versionName
}

/**
 * 获取像素密集度参数density
 *
 * @return density
 */
fun Context.density(): Float {
    return resources.displayMetrics.density
}


/**
 * 检查设备是否有虚拟键盘
 */
fun Context.checkDeviceHasNavigationBar(): Boolean {
    var hasNavigationBar = false
    val rs = this.resources
    val id = rs
            .getIdentifier("config_showNavigationBar", "bool", "android")
    if (id > 0) {
        hasNavigationBar = rs.getBoolean(id)
    }
    try {
        val systemPropertiesClass = Class
                .forName("android.os.SystemProperties")
        val m = systemPropertiesClass.getMethod("get", String::class.java)
        val navBarOverride = m.invoke(systemPropertiesClass,
                "qemu.hw.mainkeys") as String
        if ("1" == navBarOverride) {
            hasNavigationBar = false
        } else if ("0" == navBarOverride) {
            hasNavigationBar = true
        }
    } catch (e: Exception) {

    }

    return hasNavigationBar

}

fun Context.showDialog(content: String, contentColor: Int = Color.parseColor("#333333"), positiveColor: Int = Color.parseColor("#3F51B5")) {
    MaterialDialog.Builder(this)
            .content(content)
            .contentColor(contentColor)
            .positiveText("确定")
            .positiveColor(positiveColor)
            .show()
}

fun Context.showDialog(str: String, r: () -> Unit) {
    MaterialDialog.Builder(this)
            .content(str)
            .contentColorRes(R.color.dialog)
            .positiveText("确定")
            .onPositive { _, _ -> kotlin.run(r) }
            .positiveColorRes(R.color.dialogPrimary)
            .negativeText("取消")
            .negativeColorRes(R.color.dialogPrimary)
            .show()
}