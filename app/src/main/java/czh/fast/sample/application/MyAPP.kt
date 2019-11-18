package czh.fast.sample.application

import android.content.Context
import com.raizlabs.android.dbflow.config.FlowManager
import android.support.multidex.MultiDex
import czh.fast.lib.application.BaseAPP


class MyAPP : BaseAPP() {
    override fun onCreate() {
        super.onCreate()
        FlowManager.init(this)
        instance = this
    }


    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }

    companion object {

        /**
         * 全局的上下文
         */
        /**
         * 获取context

         * @return
         */
        lateinit var instance: Context
    }
}