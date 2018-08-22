package czh.fast.sample.mvp.ui.fragment

import android.content.Context
import android.net.Uri
import android.view.View
import android.widget.ImageView
import com.facebook.drawee.view.SimpleDraweeView
import com.youth.banner.loader.ImageLoader
import czh.fast.lib.utils.GlideImageLoader
import czh.fast.sample.base.AnkoLazyFragment
import czh.fast.sample.mvp.contract.NetContract
import czh.fast.sample.mvp.model.Banner
import czh.fast.sample.mvp.presenter.NetPresenter
import czh.fast.sample.mvp.ui.layout.fragment.NetFragmentUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx

class NetFragment : AnkoLazyFragment(), NetContract.View {
    val ui = NetFragmentUI()
    override fun UI(): View {
        return ui.createView(AnkoContext.create(ctx, this))
    }


    override var presenter: NetContract.Presenter = NetPresenter(this)


    override fun afterInitView() {

    }

    override fun showResult(banner: Banner) {

        val images = arrayListOf<String>()
        banner.data.forEach {
            images.add(it.image)
        }
        ui.banner.setImages(images).setImageLoader(object : ImageLoader() {
            override fun displayImage(context: Context, path: Any, imageView: ImageView) {
                imageView.setImageURI(Uri.parse(path as String))
            }

            override fun createImageView(context: Context?): ImageView {
                return SimpleDraweeView(context)
            }
        }).start()


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
