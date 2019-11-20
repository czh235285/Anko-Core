package czh.fast.lib.base

import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import czh.fast.lib.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseRetrofitClient {

    companion object {
        private const val TIME_OUT = 12
    }

    val client: OkHttpClient
        get() {
            val builder = OkHttpClient.Builder()
            handleBuilder(builder)
            val httpLoggingInterceptor = LoggingInterceptor.Builder()
                    .loggable(BuildConfig.DEBUG)
                    .setLevel(Level.BASIC)
                    .log(Platform.INFO)
                    .request("Requests")
                    .response("Response")
                    .addQueryParam("query", "0")
                    .build()
            builder.addInterceptor(httpLoggingInterceptor)
                    .connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
            return builder.build()
        }

    protected abstract fun handleBuilder(builder: OkHttpClient.Builder)

    inline fun <reified T> getService(baseUrl: String): T {
        return Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build().create(T::class.java)
    }
}