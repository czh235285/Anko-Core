package c.core.utils


import android.annotation.SuppressLint

/**
 * Created by Dell on 2017/9/15.
 */


object LightStatusBarUtils {

    fun setLightStatusBar(activity: android.app.Activity, dark: Boolean) {
        when (RomUtils.lightStatausBarAvailableRomType) {
            RomUtils.AvailableRomType.MIUI -> setMIUILightStatusBar(activity, dark)

            RomUtils.AvailableRomType.FLYME -> setFlymeLightStatusBar(activity, dark)

            RomUtils.AvailableRomType.ANDROID_NATIVE -> setAndroidNativeLightStatusBar(activity, dark)

            RomUtils.AvailableRomType.NA -> {
            }
        }// N/A do nothing
    }

    @SuppressLint("PrivateApi")
    private fun setMIUILightStatusBar(activity: android.app.Activity, darkmode: Boolean): Boolean {
        val clazz = activity.window.javaClass
        try {
            var darkModeFlag = 0
            val layoutParams = Class.forName("android.view.MiuiWindowManager\$LayoutParams")
            val field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE")
            darkModeFlag = field.getInt(layoutParams)
            val extraFlagField = clazz.getMethod("setExtraFlags", Int::class.javaPrimitiveType, Int::class.javaPrimitiveType)
            extraFlagField.invoke(activity.window, if (darkmode) darkModeFlag else 0, darkModeFlag)
            return true
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return false
    }

    private fun setFlymeLightStatusBar(activity: android.app.Activity?, dark: Boolean): Boolean {
        var result = false
        if (activity != null) {
            try {
                val lp = activity.window.attributes
                val darkFlag = android.view.WindowManager.LayoutParams::class.java
                    .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON")
                val meizuFlags = android.view.WindowManager.LayoutParams::class.java
                    .getDeclaredField("meizuFlags")
                darkFlag.isAccessible = true
                meizuFlags.isAccessible = true
                val bit = darkFlag.getInt(null)
                var value = meizuFlags.getInt(lp)
                if (dark) {
                    value = value or bit
                } else {
                    value = value and bit.inv()
                }
                meizuFlags.setInt(lp, value)
                activity.window.attributes = lp
                result = true
            } catch (e: Exception) {
            }

        }
        return result
    }

    private fun setAndroidNativeLightStatusBar(activity: android.app.Activity, dark: Boolean) {
        val decor = activity.window.decorView
        if (dark) {
            decor.systemUiVisibility = android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            decor.systemUiVisibility = 0
        }
    }

}