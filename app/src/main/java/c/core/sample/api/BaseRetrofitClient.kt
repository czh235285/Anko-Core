package c.core.sample.api

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
            val loggingInterceptor =

                HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                    override fun log(message: String) {
                        Log.i("okHttp", message)
                    }
                })
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
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