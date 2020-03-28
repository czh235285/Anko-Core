package czh.fast.sample.mvp.ui.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.ImageViewTarget
import com.pawegio.kandroid.setHeight
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import czh.adapter.AnkoMultiAdapter
import czh.adapter.holer.AnkoViewHolder
import czh.fast.lib.utils.dateToString
import czh.fast.lib.utils.gone
import czh.fast.lib.widget.viewpager.Mu5Interface
import czh.fast.sample.application.MyAPP
import czh.fast.sample.mvp.model.MultiData
import czh.fast.sample.mvp.ui.layout.item.BannerUI
import czh.fast.sample.mvp.ui.layout.item.DetailsUI
import czh.fast.sample.mvp.ui.layout.item.RcvUI
import czh.fast.sample.utils.load
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.backgroundDrawable

class MultiAdapter(val Height: Int, data: MutableList<MultiData>) : AnkoMultiAdapter<MultiData>(data) {
    init {
        addType(1, BannerUI())
        addType(2, DetailsUI())
        addType(3, RcvUI())
    }

    override fun ankoLayout(viewType: Int): AnkoComponent<Context> {
        return when (viewType) {
            1 -> BannerUI()
            2 -> DetailsUI()
            else -> RcvUI()
        }
    }


    override fun convert(holder: AnkoViewHolder, position: Int, item: MultiData?) {

        when (holder.itemViewType) {
            1 -> {
                with(holder.ui as BannerUI) {
                    mu5ViewPager.setHeight(Height)
                    item?.data?.imgs?.split(",")?.let { urls ->
                        tvIndex.text = "1/${urls.size}"
                        mu5ViewPager.setData(urls, object : Mu5Interface {
                            override fun onIndexChange(position: Int) {
                                tvIndex.text = "${position + 1}/${urls.size}"
                            }

                            override fun onLoadImage(iv: ImageView, url: String, position: Int) {


                                Glide.with(MyAPP.instance).load(url).asBitmap().apply {
                                    into(object : ImageViewTarget<Bitmap>(iv) {
                                        override fun setResource(resource: Bitmap?) {
                                            resource?.let {
                                                mu5ViewPager.bindSource(it, position, iv)
                                            }

                                        }

                                    })
                                }

                            }
                        })


                    }


                }
            }
            2 -> {
                with(holder.ui as DetailsUI) {
                    item?.data?.let {
                        civ.load(it.avatar)
                        name.text = it.nickname
                        val tags: MutableList<String> = arrayListOf()
                        it.shareTags.forEach {
                            tags.add(it.tagsDesc)
                        }
                        if (tags.size != 0) {
                            taglayout.adapter = object : TagAdapter<String>(tags) {
                                override fun getView(parent: FlowLayout?, position: Int, t: String?): View {
                                    return TextView(name.context).apply {
                                        text = t
                                        setTextColor(Color.parseColor("#a9a9a9"))
                                        setPadding(45, 10, 45, 10)
                                        backgroundDrawable = GradientDrawable().apply {
                                            gradientType = GradientDrawable.LINEAR_GRADIENT
                                            setColor(Color.parseColor("#ffffff"))
                                            cornerRadius = 45f
                                            setStroke(2, Color.parseColor("#a9a9a9"))
                                        }
                                    }
                                }
                            }
                        } else {
                            taglayout.gone()
                        }

                        tvTitle.text = it.title
                        tvContent.text = it.contentTop
                        tvTime.text = (it.createTime.toLong() * 1000).dateToString("MM-dd HH:mm")
                        tvNumber.text = it.collectionsNum + "人想买"


                    }


                }
            }
            3 -> {
                with(holder.ui as RcvUI) {
                    item?.hot?.let {
                        val adapter = GoodAdapter(it)
                        rcv.adapter = adapter
                    }

                }
            }

        }

    }

}