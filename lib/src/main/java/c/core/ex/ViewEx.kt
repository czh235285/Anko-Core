package c.core.ex

import android.view.View


fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.setVisibleOrInVisible(isShow: Boolean) {
    this.visibility = if (isShow) View.VISIBLE else View.INVISIBLE
}

fun View.setVisibleOrGone(isShow: Boolean) {
    this.visibility = if (isShow) View.VISIBLE else View.GONE
}