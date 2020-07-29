package c.core.widget.viewpager

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

/**
 * Created by gjm on 2017/7/26.
 * Func:
 */

object Utils {

    /**
     * 获得屏幕宽度
     *
     * @param context
     * @return
     */
    fun getDisplayWidth(context: Context): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(outMetrics)
        return outMetrics.widthPixels
    }


    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }
}
