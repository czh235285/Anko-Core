package czh.fast.sample.mvp.ui.fragment

import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import czh.fast.lib.utils.FrescoImageLoader
import czh.fast.lib.utils.anko.setContentView
import czh.fast.sample.base.AnkoLazyFragment
import czh.fast.sample.mvp.contract.NetContract
import czh.fast.sample.mvp.model.Banner
import czh.fast.sample.mvp.presenter.NetPresenter
import czh.fast.sample.mvp.ui.layout.fragment.NetFragmentUI
import czh.fast.sample.mvp.viewmodel.NetViewModel
import czh.fast.sample.utils.selectedDrawable
import czh.fast.sample.utils.unSelectedDrawable

class NetFragment : AnkoLazyFragment(), NetContract.View {

    val viewModel by viewModels<NetViewModel>()

    override fun showResult(banner: Banner) {

        val images = arrayListOf<String>()
        banner.data?.forEach {
            images.add(it.imagePath ?: "")
        }

        ui.banner.setIndicatorDrawable(selectedDrawable, unSelectedDrawable, 30, 30)
        ui.banner.setImages(images).setImageLoader(FrescoImageLoader()).start()
    }


    val ui = NetFragmentUI()
    var presenter = NetPresenter(this)
    override fun layout(): View {
        return ui.setContentView(this)
    }

    override fun afterInitView() {
        viewModel.mBanner.observe(this, Observer {
            val images = arrayListOf<String>()
            it.data?.forEach {
                images.add(it.imagePath ?: "")
            }

            ui.banner.setIndicatorDrawable(selectedDrawable, unSelectedDrawable, 30, 30)
            ui.banner.setImages(images).setImageLoader(FrescoImageLoader()).start()
        })

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
