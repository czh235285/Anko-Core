package czh.fast.lib.application

import android.app.Application
import android.content.Context
import com.vise.log.ViseLog
import com.vise.log.inner.LogcatTree
import com.vise.utils.assist.SSLUtil
import com.vise.xsnow.http.ViseHttp
import com.vise.xsnow.http.interceptor.HttpLogInterceptor
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

/**
//┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃ 　
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//    ┃　　　┃   神兽保佑　　　　　　　　
//    ┃　　　┃   代码无BUG！
//    ┃　　　┗━━━┓
//    ┃　　　　　　　┣┓
//    ┃　　　　　　　┏┛
//    ┗┓┓┏━┳┓┏┛
//      ┃┫┫　┃┫┫
//      ┗┻┛　┗┻┛

 * Created by Dell on 2018/5/20
 * @author czh.
 */


abstract class BaseApp : Application() {
    abstract val BaseUrl: String

    override fun onCreate() {
        super.onCreate()
        initLog()
        initNet()
    }


    private fun initLog() {
        ViseLog.getLogConfig()
                .configAllowLog(true)//是否输出日志
                .configShowBorders(false)//是否排版显示
        ViseLog.plant(LogcatTree())//添加打印日志信息到Logcat的树
    }

    private fun initNet() {
        ViseHttp.init(this)
        ViseHttp.CONFIG()
                .baseUrl(BaseUrl)
                .globalHeaders(HashMap<String, String>())
                .globalParams(HashMap<String, String>())
                .readTimeout(30)
                .writeTimeout(30)
                .connectTimeout(30)
                .retryCount(3)
                .retryDelayMillis(6000)
                .setCookie(true)
                .interceptor(HttpLogInterceptor()
                        .setLevel(HttpLogInterceptor.Level.BODY))
                .converterFactory(GsonConverterFactory.create())
                .callAdapterFactory(RxJava2CallAdapterFactory.create())
                .SSLSocketFactory(SSLUtil.getSslSocketFactory(null, null, null))

    }

}