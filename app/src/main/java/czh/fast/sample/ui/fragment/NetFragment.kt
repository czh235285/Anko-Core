package czh.fast.sample.ui.fragment

import android.view.View
import com.pawegio.kandroid.e
import com.vise.xsnow.http.ViseHttp
import com.vise.xsnow.http.core.ApiTransformer
import com.vise.xsnow.http.mode.CacheMode
import czh.fast.lib.utils.GlideImageLoader
import czh.fast.sample.R
import czh.fast.sample.base.LazyFragment
import czh.fast.sample.ui.model.Advert
import kotlinx.android.synthetic.main.fragment_net.*

class NetFragment : LazyFragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v) {
            tvNormal -> {
                showLoading()
                apiservice.getAdvert().compose(ApiTransformer.norTransformer()).subscribe(
                        {
                            stopLoading()
                            showBanner(it)

                        }, {
                    stopLoading()
                    e { it.message.toString() }
                }
                )
            }
            tvCache -> {
                apiservice.getAdvert()
                        .compose(ApiTransformer.norTransformer())
                        .compose(ViseHttp.getApiCache().transformer(CacheMode.CACHE_AND_REMOTE, Advert::class.java))
                        .subscribe(
                                {
                                    stopLoading()
                                    showBanner(it.cacheData)

                                }, {
                            stopLoading()
                            e { it.message.toString() }
                        }
                        )
            }
        }
    }

    private fun showBanner(it: Advert) {
        val images = arrayListOf<String>()
        it.data.forEach {
            if (it.adname == "限时热卖") {
                images.add("https://pearshare.oss-cn-shanghai.aliyuncs.com/" + it.face)
            }
        }
        banner.setImages(images).setImageLoader(GlideImageLoader()).start()
    }

    override val layoutResource: Int
        get() = R.layout.fragment_net

    override fun afterInitView() {
        tvNormal.setOnClickListener(this)
        tvCache.setOnClickListener(this)
    }

    companion object {
        fun get(): NetFragment {
            return Inner.anotherSingle
        }

        private object Inner {
            val anotherSingle = NetFragment()
        }
    }
}
