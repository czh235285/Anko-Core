package czh.fast.sample.api

import czh.fast.sample.mvp.model.Advert
import com.vise.xsnow.http.ViseHttp
import io.reactivex.Observable
import retrofit2.http.GET


/**
 * Created by Administrator on 2017/3/9.
 */

val apiservice by lazy { ViseHttp.RETROFIT<Any>().create<ApiService>(ApiService::class.java) }

interface ApiService {

    /**
     * 获取轮播图
     */
    @GET("api/advert/list")
    fun getAdvert(): Observable<Advert>
}
