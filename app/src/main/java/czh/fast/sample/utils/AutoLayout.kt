package czh.fast.sample.utils


import android.view.View
import android.view.ViewGroup

import com.zhy.autolayout.utils.AutoUtils;

class AutoLayout {
    companion object {
        fun auto(view: View): View {
            if (view is ViewGroup) {
                for (i in 0 until view.childCount) {
                    auto(view.getChildAt(i))
                }
            }
            AutoUtils.auto(view)
            return view
        }
    }
}