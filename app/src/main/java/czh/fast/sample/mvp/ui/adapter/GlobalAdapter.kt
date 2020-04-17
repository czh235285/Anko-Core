package czh.fast.sample.mvp.ui.adapter

import android.view.View
import com.vise.log.ViseLog
import czh.fast.lib.widget.Gloading
import czh.fast.sample.widget.StatusView

class GlobalAdapter : Gloading.Adapter {
    override fun getView(holder: Gloading.Holder, convertView: View?, status: Int): View {
        var loadingStatusView: StatusView? = null
        //reuse the old view, if possible
        //reuse the old view, if possible
        if (convertView != null && convertView is StatusView) {
            loadingStatusView = convertView

            ViseLog.d(" loadingStatusView = convertView")
        }
        if (loadingStatusView == null) {
            loadingStatusView = StatusView(holder.context, holder.retryTask)
            ViseLog.d("  loadingStatusView = StatusView(holder.context, holder.retryTask)")
        }
        loadingStatusView.setStatus(status)

        return loadingStatusView;

//        return holder.context.UI {
//            relativeLayout {
//                layoutParams = FrameLayout.LayoutParams(matchParent, matchParent)
//
//                textView {
//                    backgroundColor = Color.parseColor("#ff0000")
//                    text = "空布局"
//                    textColor = Color.parseColor("#fff000")
//                    gone()
////                    if (status == Gloading.STATUS_LOAD_SUCCESS) {
////                        gone()
////                    } else {
////                        visible()
////                    }
//                }.lparams(matchParent, matchParent)
//            }
//
//        }.view


    }

}