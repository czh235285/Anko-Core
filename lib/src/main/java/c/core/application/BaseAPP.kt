package c.core.application

import android.app.Application
import com.vise.log.ViseLog
import com.vise.log.inner.LogcatTree

abstract class BaseAPP : Application() {

    override fun onCreate() {
        super.onCreate()
        initLog()
    }

    private fun initLog() {
        ViseLog.getLogConfig().configAllowLog(true)
            .configShowBorders(false)
        ViseLog.plant(LogcatTree())
    }
}