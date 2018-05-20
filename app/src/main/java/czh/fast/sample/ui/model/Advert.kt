package czh.fast.sample.ui.model

import java.io.Serializable

/**
 * Created by admin on 2018/4/18.
 */


data class Advert(
        val code: Int,
        val msg: String,
        val data: List<AdvertData>,
        val totalCount: Int,
        val curPage: Int,
        val pageSize: Int,
        val success: Boolean
)

data class AdvertData(
        val advertid: Int,
        val adname: String,
        val postion: Int,
        val face: String,
        val adtype: Int,
        val itemid: Int,
        val enable: Int,
        val orderindex: Int,
        val url: String,
        val showdate: Long,
        val subdate: Long,
        val content: String
) : Serializable