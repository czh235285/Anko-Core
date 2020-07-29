package c.core.widget.status

import android.view.View

class GlobalAdapter : Gloading.Adapter {
    override fun getView(holder: Gloading.Holder, convertView: View?, status: Int): View {
        var statusView: StatusView? = null
        if (convertView != null && convertView is StatusView) {
            statusView = convertView
        }
        if (statusView == null) {
            statusView = StatusView(holder.context, holder.retryTask)
        }
        statusView.setStatus(status)

        return statusView
    }
}