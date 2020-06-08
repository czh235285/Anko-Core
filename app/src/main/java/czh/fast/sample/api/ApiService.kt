package czh.fast.sample.api

import czh.fast.sample.mvp.model.Banner
import retrofit2.http.GET



val apiservice by lazy { RetrofitClient.getService<ApiService>("https://www.wanandroid.com/") }


interface ApiService {

    /**
     *广告轮播
     */
    @GET("banner/json")
    suspend fun getBanner(): Banner
}
