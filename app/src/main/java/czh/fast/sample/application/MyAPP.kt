package czh.fast.sample.application

import android.content.Context
import androidx.multidex.MultiDex
import com.raizlabs.android.dbflow.config.FlowManager
import czh.fast.lib.application.BaseAPP
import czh.fast.lib.widget.Gloading
import czh.fast.sample.mvp.ui.adapter.GlobalAdapter


class MyAPP : BaseAPP() {
    override fun onCreate() {
        super.onCreate()
        FlowManager.init(this)
        Gloading.initDefault(GlobalAdapter())
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