package czh.fast.sample.mvp.model


data class Banner(
        val success: Boolean,
        val data: List<BannerData>,
        val timestamp: Int,
        val errMsg: String,
        val errCode: Int?
)



data class BannerData(
        var id: String,
        var title: String,
        var image: String,
        var pos: String,
        var url: String,
        var data: String,
        var begin: String,
        var end: String,
        var status: String,
        var create_time: String
)
