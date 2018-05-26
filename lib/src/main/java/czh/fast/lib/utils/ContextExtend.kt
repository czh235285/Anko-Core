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

fun Context.showDialog(str: String) {
    MaterialDialog.Builder(this)
            .content(str)
            .contentColorRes(R.color.white)
            .positiveText("确定")
            .positiveColorRes(R.color.colorPrimary)
            .show()
}

fun Context.showDialog(str: String, r: () -> Unit) {
    MaterialDialog.Builder(this)
            .content(str)
            .contentColorRes(R.color.dialog)
            .positiveText("确定")
            .onPositive { _, _ -> kotlin.run(r) }
            .positiveColorRes(R.color.colorPrimaryDark)
            .negativeText("取消")
            .negativeColorRes(R.color.colorPrimaryDark)
            .show()
}