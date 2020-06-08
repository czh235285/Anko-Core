package czh.fast.sample.mvp.model

 data class Banner(
    var data: List<BannerData>?,
    var errorCode: Int?,
    var errorMsg: String?
)

data class BannerData(
    var desc: String?,
    var id: Int?,
    var imagePath: String?,
    var isVisible: Int?,
    var order: Int?,
    var title: String?,
    var type: Int?,
    var url: String?
)