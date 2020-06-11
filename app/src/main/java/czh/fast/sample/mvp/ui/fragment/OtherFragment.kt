package czh.fast.sample.mvp.ui.fragment

import android.content.Context
import android.view.View
import czh.fast.sample.base.AnkoLazyFragment
import czh.fast.sample.mvp.ui.layout.fragment.OtherFragmentUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx
import android.net.Uri
import czh.adapter.AnkoAdapter
import czh.adapter.holer.AnkoViewHolder
import czh.fast.lib.utils.noDoubleClick
import czh.fast.lib.utils.openActivity
import czh.fast.sample.mvp.ui.activity.AdaptationActivity
import czh.fast.sample.mvp.ui.activity.ImageActivity
import czh.fast.sample.mvp.ui.activity.MultiActivity
import czh.fast.sample.mvp.ui.activity.UpdateActivity
import czh.fast.sample.mvp.ui.layout.item.ItemUI
import org.jetbrains.anko.AnkoComponent


class OtherFragment : AnkoLazyFragment() {
    val ui = OtherFragmentUI()
    override fun layout(): View {
        return ui.createView(AnkoContext.create(ctx, this))
    }

    val mAdapter by lazy {
        object : AnkoAdapter<String>(arrayListOf("图片", "多Type布局", "适配","版本更新")) {

            override fun ankoLayout(viewType: Int): AnkoComponent<Context> {
                return ItemUI()
            }

            override fun convert(holder: AnkoViewHolder, position: Int, item: String?) = with(holder.ui as ItemUI) {
                tv.text=item
                tv.noDoubleClick {
                    when (position) {
                        0 -> {
                            mContext.openActivity<ImageActivity>()
                        }
                        1 -> {
                            mContext.openActivity<MultiActivity>()
                        }
                        2 -> {
                            mContext.openActivity<AdaptationActivity>()
                        }
                        3 -> {
                            mContext.openActivity<UpdateActivity>()
                        }
                    }
                }
            }
        }
    }

    override fun afterInitView() {
        ui.rcv.adapter = mAdapter
    }


    companion object {
        fun get(): OtherFragment {
            return Inner.anotherSingle
        }

        private object Inner {
            val anotherSingle = OtherFragment()
        }
    }
}
