package czh.fast.sample.api

import com.vise.xsnow.http.ViseHttp
import czh.fast.sample.mvp.model.Banner
import io.reactivex.Observable
import retrofit2.http.GET


/**
 * Created by Administrator on 2017/3/9.
 */

val apiservice by lazy { ViseHttp.RETROFIT<Any>().create<ApiService>(ApiService::class.java) }

interface ApiService {

    /**
     *广告轮播
     */
    @GET("v1/article/getAppBanner")
    fun getBanner(): Observable<Banner>
}
