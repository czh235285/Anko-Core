package czh.fast.sample.mvp.ui.adapter

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.jakewharton.rxbinding2.view.RxView
import com.wx.goodview.GoodView

import org.jetbrains.anko.AnkoComponent

import czh.adapter.AnkoAdapter
import czh.adapter.holer.BaseViewHolder
import czh.fast.lib.utils.load
import czh.fast.lib.utils.norTransformer
import czh.fast.lib.utils.toast
import czh.fast.sample.mvp.model.HotData
import czh.fast.sample.mvp.ui.layout.item.GoodsItemUI
import czh.fast.sample.utils.SpannableStringUtils
import czh.library.LikeView
import io.reactivex.android.schedulers.AndroidSchedulers
import org.jetbrains.anko.find
import java.util.concurrent.TimeUnit

class GoodAdapter(ui: AnkoComponent<Context>, mData: List<HotData>) : AnkoAdapter<HotData>(ui, mData) {

    private val gv by lazy {
        GoodView(mContext).apply {
            setTextInfo("想买+1", Color.RED, 16)
            setDuration(1000)
        }
    }

    override fun convert(holder: BaseViewHolder, ui: AnkoComponent<Context>, item: HotData?) {
        with(ui as GoodsItemUI) {
            //需要单独对某个控件进行操作，都需要自己设置id，自己find，否则有BUG，anko的问题。。。
            val likeView = holder.itemView.find<LikeView>(2)
            val tvWantToBuy = holder.itemView.find<TextView>(3)
            val rl = holder.itemView.find<View>(4)
            if (holder.layoutPosition - getHeaderLayoutCount() == 1) {
                riv.aspectRatio = 1f
            } else {
                riv.aspectRatio = 0.76f
            }
            item?.cover?.let {
                riv.load(it, 400, 600)
            }
            tvWantToBuy.text = item?.collectionsNum

            likeView.isClickable = item?.isCollections == "1"
            RxView.clicks(rl).throttleFirst(400, TimeUnit.MILLISECONDS).subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            {

                                if (likeView.isChecked) {
                                    likeView.unLike()
                                    tvWantToBuy.text = (tvWantToBuy.text.toString().toInt() - 1).toString()
                                } else {
                                    gv.show(rl)
                                    likeView.like()
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
}
