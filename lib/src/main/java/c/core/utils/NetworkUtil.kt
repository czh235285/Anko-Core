package c.core.utils

import android.content.Context
import android.net.ConnectivityManager
import c.core.ex.appCtx

/**
 * 判断网络是否可用
 */
@Suppress("DEPRECATION")
val isNetworkAvailable: Boolean
    get() {
        val cm = appCtx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        return networkInfo?.isConnected == true
    }

/**
 * 检测wifi是否连接
 */
@Suppress("DEPRECATION")
val isWifiConnected: Boolean
    get() {
        val cm = appCtx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        return networkInfo?.type == ConnectivityManager.TYPE_WIFI
    }

/**
 * 检测3G是否连接
 */
@Suppress("DEPRECATION")
val is3gConnected: Boolean
    get() {
        val cm = appCtx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        return networkInfo?.type == ConnectivityManager.TYPE_MOBILE
    }
