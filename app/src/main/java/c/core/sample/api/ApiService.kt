package c.core.sample.api

import c.core.sample.model.Banner
import c.core.sample.model.Project
import retrofit2.http.GET
import retrofit2.http.Path


val apiservice by lazy { RetrofitClient.getService<ApiService>("https://www.wanandroid.com/") }

interface ApiService {

    /**
     *广告轮播
     */
    @GET("banner/json")
    suspend fun getBanner(): Banner

    /**
     *广告轮播
     */
    @GET("project/list/{page}/json?cid=294")
    suspend fun getProject(@Path("page") page: Int): Project
}
