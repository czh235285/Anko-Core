package czh.fast.sample.api

import czh.fast.sample.mvp.model.Banner
import retrofit2.http.GET



val apiservice by lazy { RetrofitClient.getService(ApiService::class.java, "http://api.test.meb.com/app/v7/") }


interface ApiService {

    /**
     *广告轮播
     */
    @GET("home/AndroidIndex")
    suspend fun getBanner(): Banner
}
