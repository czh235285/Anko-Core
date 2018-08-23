package czh.fast.sample.mvp.model

data class Data(
        var shareId: String,
        var imgs: String,
        var userId: String,
        var avatar: String,
        var nickname: String,
        var isFollow: String,
        var title: String,
        var subTitle: String,
        var shareTags: List<ShareTag>,
        var contentImg: String,
        var contentTop: String,
        var contentBottom: String,
        var allowComments: String,
        var collectionsNum: String,
        var groupStatus: String,
        var isCollections: String,
        var goodsAmt: String,
        var goodsUrl: String,
        var goodsId: String,
        var createTime: String,
        var comments: Comments
)