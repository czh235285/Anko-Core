package czh.fast.sample.mvp.ui.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import czh.fast.lib.utils.FrescoImageLoader
import czh.fast.sample.base.AnkoLazyFragment
import czh.fast.sample.mvp.contract.NetContract
import czh.fast.sample.mvp.model.Banner
import czh.fast.sample.mvp.presenter.NetPresenter
import czh.fast.sample.mvp.ui.layout.fragment.NetFragmentUI
import czh.fast.sample.utils.selectedDrawable
import czh.fast.sample.utils.unSelectedDrawable
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx

class NetFragment : AnkoLazyFragment(), NetContract.View {
    override fun showResult(banner: Banner) {

        val images = arrayListOf<String>()
        banner.Content.Advert.forEach {
            images.add(it.Picture)
        }

        ui.banner.setIndicatorDrawable(selectedDrawable, unSelectedDrawable, 30, 30)
        ui.banner.setImages(images).setImageLoader(FrescoImageLoader()).start()
    }


    val ui = NetFragmentUI()
    var presenter = NetPresenter(this)
    override fun UI(): View {
        return ui.createView(AnkoContext.create(ctx, this))
    }

    override fun afterInitView() {

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.cancel()
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
