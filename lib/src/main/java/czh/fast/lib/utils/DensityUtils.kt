package czh.fast.lib.utils

import android.app.Activity
import android.app.Application
import android.content.ComponentCallbacks
import android.content.res.Configuration
import android.util.DisplayMetrics

object DensityUtils {
    private var sNonCompatDensity: Float = 0.toFloat()
    private var sNonCompatScaleDensity: Float = 0.toFloat()

    fun setCustomDensity(activity: Activity, application: Application) {
        val appDisplayMetrics = application.resources.displayMetrics
        if (sNonCompatDensity == 0f) {
            sNonCompatDensity = appDisplayMetrics.density
            sNonCompatScaleDensity = appDisplayMetrics.scaledDensity
            application.registerComponentCallbacks(object : ComponentCallbacks {
                override fun onConfigurationChanged(newConfig: Configuration) {
                    sNonCompatScaleDensity = application.resources.displayMetrics
                            .scaledDensity
                }

                override fun onLowMemory() {}
            })
        } //屏幕宽 的像素除以360 获得Density的值
        val targetDensity = (appDisplayMetrics.widthPixels / 360).toFloat()
        val targetDensityDpi = (targetDensity * 160).toInt()
        val targetScaledDensity = (targetDensity * (sNonCompatScaleDensity / sNonCompatDensity)).toInt().toFloat()
        appDisplayMetrics.density = targetDensity
        appDisplayMetrics.scaledDensity = targetScaledDensity
        appDisplayMetrics.densityDpi = targetDensityDpi

        val activityDisplayMetrics = application.resources
                .displayMetrics
        activityDisplayMetrics.density = targetDensity
        activityDisplayMetrics.scaledDensity = targetScaledDensity
        activityDisplayMetrics.densityDpi = targetDensityDpi
    }

}
