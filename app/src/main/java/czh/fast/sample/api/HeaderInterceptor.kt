package czh.fast.sample.api

import okhttp3.Interceptor
import okhttp3.Response

/**
 * 通用header
 */
class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
                .newBuilder()
                .addHeader("test", "testHeader")
                .build()
        return chain.proceed(request)
    }

}