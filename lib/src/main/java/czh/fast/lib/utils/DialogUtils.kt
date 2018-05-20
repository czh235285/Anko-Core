package czh.fast.lib.utils

import android.content.Context
import com.afollestad.materialdialogs.MaterialDialog
import czh.fast.lib.R

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
