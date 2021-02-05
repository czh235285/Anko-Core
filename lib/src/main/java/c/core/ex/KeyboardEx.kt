package c.core.ex

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import org.jetbrains.anko.inputMethodManager

fun Activity.showSoftInput() {
    val view: View? = currentFocus ?: window.decorView
    view?.showSoftInput()
}

fun Activity.hideSoftInput() {
    val view: View? = currentFocus ?: window.decorView
    inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
}

fun Activity.isSoftInputActive(): Boolean {
    return inputMethodManager.isActive
}

fun Fragment.showSoftInput() {
    requireActivity().showSoftInput()
}

fun Fragment.hideSoftInput() {
    requireActivity().hideSoftInput()
}

fun Fragment.isSoftInputActive() {
    requireActivity().isSoftInputActive()
}

fun View.showSoftInput() {
    isFocusable = true
    isFocusableInTouchMode = true
    requestFocus()
    context.inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_FORCED)
}

fun View.hideSoftInput() {
    isFocusable = false
    isFocusableInTouchMode = false
    context.inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.isSoftInputActive() {
    context.inputMethodManager.isActive
}
