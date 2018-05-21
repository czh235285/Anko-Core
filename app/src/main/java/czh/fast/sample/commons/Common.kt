package czh.fast.sample.commons

import com.vise.xsnow.http.ViseHttp
import czh.fast.sample.api.ApiService

val apiservice = ViseHttp.RETROFIT<Any>().create<ApiService>(ApiService::class.java)