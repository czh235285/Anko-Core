package czh.fast.sample.mvp.ui.activity

import android.graphics.Bitmap
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.Gson
import com.vise.log.ViseLog
import org.jetbrains.anko.setContentView


import czh.fast.sample.base.AnkoActivity
import czh.fast.sample.mvp.model.Com
import czh.fast.sample.mvp.model.Data
import czh.fast.sample.mvp.model.Hot
import czh.fast.sample.mvp.model.MultiData
import czh.fast.sample.mvp.ui.adapter.MultiAdapter
import czh.fast.sample.mvp.ui.layout.activity.MultiActivityUI
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.Glide
import com.bumptech.glide.request.transition.Transition
import czh.fast.lib.utils.screenWidth


class MultiActivity : AnkoActivity() {
    val mList: MutableList<MultiData> = arrayListOf()
    val ui = MultiActivityUI()
    var mAdapter: MultiAdapter? = null
    override fun ankoLayout() {
        ui.setContentView(this)
    }

    override fun afterInitView() {
        val data = Gson().fromJson(Com.data, Data::class.java)
        val hot = Gson().fromJson(Com.hot, Hot::class.java).hotData
        mList.add(MultiData(1, data))
        mList.add(MultiData(2, data))
        mList.add(MultiData(3, hot))
        Glide.with(this)
                .asBitmap()
                .load(data.imgs.split(",")[0])
                //强制Glide返回一个Bitmap对象
                .into(object : SimpleTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        mAdapter = MultiAdapter(screenWidth() * resource.height / resource.width, mList)
                        ui.rcv.apply {
                            layoutManager = LinearLayoutManager(this@MultiActivity)
                            adapter = mAdapter
                        }
                    }
                })


    }

    override fun onDestroy() {
        super.onDestroy()
        mAdapter = null
    }


}
