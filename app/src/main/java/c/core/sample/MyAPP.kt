package c.core.sample

import android.content.Context
import androidx.multidex.MultiDex
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import c.core.application.BaseAPP
import c.core.utils.changeAlpha
import c.core.utils.color

class MyAPP : BaseAPP() {
    override fun onCreate() {
        super.onCreate()
    }

    init {
        // 设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            layout.setPrimaryColors("#f2f6f9".color.changeAlpha(0f), "#363636".color)
            layout.setEnableAutoLoadMore(false)
            return@setDefaultRefreshHeaderCreator ClassicsHeader(context)
        }
        // 设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
            layout.setEnableLoadMoreWhenContentNotFull(false)
            layout.setEnableFooterFollowWhenNoMoreData(true)
            layout.setEnableAutoLoadMore(false)
            return@setDefaultRefreshFooterCreator ClassicsFooter(context).setDrawableSize(20f)
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }
}
