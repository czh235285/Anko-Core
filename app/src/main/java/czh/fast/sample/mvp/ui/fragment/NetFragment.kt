package czh.fast.sample.mvp.ui.fragment

import android.view.View
import czh.fast.lib.utils.GlideImageLoader
import czh.fast.sample.R
import czh.fast.lib.base.LazyFragment
import czh.fast.sample.mvp.contract.NetContract
import czh.fast.sample.mvp.model.Advert
import czh.fast.sample.mvp.presenter.NetPresenter
import kotlinx.android.synthetic.main.fragment_net.*

class NetFragment : LazyFragment(), View.OnClickListener, NetContract.View {
    override var presenter: NetContract.Presenter = NetPresenter(this)

    override val layoutResource: Int
        get() = R.layout.fragment_net

    override fun afterInitView() {
        tvNormal.setOnClickListener(this)
        tvCache.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            tvNormal -> {
                presenter.normalTask()
            }
            tvCache -> {
                presenter.cacheTask()
            }
        }
    }
    override fun showResult(advert: Advert) {
        val images = arrayListOf<String>()
        advert.data.forEach {
            if (it.adname == "限时热卖") {
                images.add("https://pearshare.oss-cn-shanghai.aliyuncs.com/" + it.face)
            }
        }
        banner.setImages(images).setImageLoader(GlideImageLoader()).start()
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
