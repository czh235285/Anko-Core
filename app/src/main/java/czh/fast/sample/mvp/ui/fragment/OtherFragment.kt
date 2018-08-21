package czh.fast.sample.mvp.ui.fragment

import android.view.View
import czh.fast.sample.base.AnkoLazyFragment
import czh.fast.sample.mvp.ui.layout.fragment.OtherFragmentUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx

class OtherFragment : AnkoLazyFragment(){
    val ui = OtherFragmentUI()
    override fun UI(): View {
        return ui.createView(AnkoContext.create(ctx, this))
    }

    override fun afterInitView() {

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
