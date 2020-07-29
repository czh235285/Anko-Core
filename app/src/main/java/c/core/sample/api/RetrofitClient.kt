package c.core.sample.api


import c.core.ex.appCtx
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File

object RetrofitClient : BaseRetrofitClient() {

    override fun handleBuilder(builder: OkHttpClient.Builder) {

        val httpCacheDirectory = File(appCtx.cacheDir, "responses")
        val cacheSize = 10 * 1024 * 1024L
        val cache = Cache(httpCacheDirectory, cacheSize)
        builder.cache(cache)
            .addInterceptor {
                val request = it.request().newBuilder()
                    .addHeader("test", "testHeader")
                    .build()
                return@addInterceptor it.proceed(request)
            }
    }
}