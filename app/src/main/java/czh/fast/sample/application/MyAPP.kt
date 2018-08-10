package czh.fast.sample.application

import android.content.Context
import com.raizlabs.android.dbflow.config.FlowManager
import czh.fast.lib.application.BaseApp
import android.support.multidex.MultiDex


class MyAPP : BaseApp() {
    override fun onCreate() {
        super.onCreate()
        FlowManager.init(this)
        instance = this
    }

    override val BaseUrl: String
        get() = "http://api-test.lifenxiang.cn/app/"


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