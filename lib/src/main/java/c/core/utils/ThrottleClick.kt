package c.core.utils

import android.os.SystemClock
import android.view.View

fun View.throttleClick(seconds: Long = 500, onNext: ((View) -> Unit)) {
    this.setOnClickListener(ThrottleClick(seconds, onNext))
}

private class ThrottleClick(private val timeout: Long, private val onNext: (View) -> Unit) :
    View.OnClickListener {
    private var lastClickTime = 0L

    override fun onClick(v: View) {
        val diff = SystemClock.elapsedRealtime() - lastClickTime
        if (diff > timeout) {
            onNext(v)
            lastClickTime = SystemClock.elapsedRealtime()
        }
    }
}
