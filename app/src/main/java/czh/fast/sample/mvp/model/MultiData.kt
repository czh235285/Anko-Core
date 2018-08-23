package czh.fast.sample.mvp.model

import czh.adapter.AnkoMultiAdapter

class MultiData : AnkoMultiAdapter.MultiItem {
    override var itemType: Int = 0

    var data: Data? = null

    var hot :List<HotData>?=null


    constructor(itemType: Int) {
        this.itemType = itemType
    }

    constructor(itemType: Int, data: Data?) {
        this.itemType = itemType
        this.data = data
    }

    constructor(itemType: Int, hot: List<HotData>?) {
        this.itemType = itemType
        this.hot = hot
    }


}