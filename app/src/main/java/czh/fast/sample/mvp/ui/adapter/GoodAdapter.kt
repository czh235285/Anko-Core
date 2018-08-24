package czh.fast.sample.mvp.ui.adapter

import android.content.Context
import android.graphics.Color
import com.jakewharton.rxbinding2.view.RxView
import com.wx.goodview.GoodView

import org.jetbrains.anko.AnkoComponent

import czh.adapter.AnkoAdapter
import czh.adapter.holer.AnkoViewHolder
import czh.fast.lib.utils.load
import czh.fast.sample.mvp.model.HotData
import czh.fast.sample.mvp.ui.layout.item.GoodsItemUI
import czh.fast.sample.utils.SpannableStringUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class GoodAdapter(mData: List<HotData>) : AnkoAdapter<HotData>(mData) {
    override fun ankoLayout(viewType: Int): AnkoComponent<Context> {
        return GoodsItemUI()
    }

    private val gv by lazy {
        GoodView(mContext).apply {
            setTextInfo("想买+1", Color.RED, 16)
            setDuration(1000)
        }
    }

    override fun convert(holder: AnkoViewHolder, position: Int, item: HotData?) = with(holder.ui as GoodsItemUI) {

        if (holder.layoutPosition - getHeaderLayoutCount() == 1) {
            riv.aspectRatio = 1f
        } else {
            riv.aspectRatio = 0.76f
        }
        item?.cover?.let {
            riv.load(it, 400, 600)
        }
        tvWantToBuy.text = item?.collectionsNum

        likeview.isClickable = item?.isCollections == "1"
        RxView.clicks(rl).throttleFirst(400, TimeUnit.MILLISECONDS).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {

                            if (likeview.isChecked) {
                                likeview.unLike()
                                tvWantToBuy.text = (tvWantToBuy.text.toString().toInt() - 1).toString()
                            } else {
                                gv.show(rl)
                                likeview.like()
                                tvWantToBuy.text = (tvWantToBuy.text.toString().toInt() + 1).toString()
                            }

                        },
                        {

                        }
                )

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
