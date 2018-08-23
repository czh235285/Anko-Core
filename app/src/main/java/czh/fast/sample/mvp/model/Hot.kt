package czh.fast.sample.mvp.model


data class Hot(
    var hotData: List<HotData>
)

data class HotData(
    var shareId: String,
    var cover: String,
    var groupStatus: String,
    var title: String,
    var subTitle: String,
    var collectionsNum: String,
    var isCollections: String,
    var userId: String,
    var avatar: String,
    var nickname: String,
    var goodsAmt: String,
    var goodsId: String
)