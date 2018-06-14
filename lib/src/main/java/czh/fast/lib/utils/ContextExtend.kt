package czh.fast.lib.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
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

fun Context.toast(message: CharSequence) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.toastLong(message: CharSequence) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.inflater(resource: Int): View {
    return LayoutInflater.from(this).inflate(resource, null)
}


fun Context.inflater(resource: Int, root: ViewGroup, attachToRoot: Boolean): View {
    return LayoutInflater.from(this).inflate(resource, root, attachToRoot)
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

fun Context.showDialog(str: String) {
    MaterialDialog.Builder(this)
            .content(str)
            .contentColorRes(R.color.white)
            .positiveText("确定")
            .positiveColorRes(R.color.dialogPrimary)
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