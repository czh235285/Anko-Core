package czh.fast.sample.mvp.ui.adapter

import android.content.Context
import android.graphics.Color

import org.jetbrains.anko.AnkoComponent

import czh.adapter.AnkoAdapter
import czh.adapter.holer.BaseViewHolder
import czh.fast.lib.utils.load
import czh.fast.sample.mvp.model.HotData
import czh.fast.sample.mvp.ui.layout.item.GoodsItemUI
import czh.fast.sample.utils.SpannableStringUtils

class GoodAdapter(ui: AnkoComponent<Context>, mData: List<HotData>) : AnkoAdapter<HotData>(ui, mData) {
    override fun convert(holder: BaseViewHolder, ui: AnkoComponent<Context>, item: HotData?) {
        with(ui as GoodsItemUI) {
            holder.setIsRecyclable(false)
            if (holder.layoutPosition - getHeaderLayoutCount() == 1) {
                riv.aspectRatio = 1f
            } else {
                riv.aspectRatio = 0.76f
            }
            item?.cover?.let {
                riv.load(it, 400, 600)
            }
            tvWantToBuy.text = item?.collectionsNum

            likeview.selectLike(item!!.isCollections == "1")
            tvTitle.text = item?.title
            if (item?.goodsAmt != "") {
                tvPrice.text = SpannableStringUtils.getBuilder("价格:").setForegroundColor(Color.parseColor("#333333"))
                        .append(item!!.goodsAmt).setForegroundColor(Color.parseColor("#ff0000"))
                        .create()
            }
            item.avatar.let {
                civ.load(it, 100, 100)
            }

        }
    }
}
