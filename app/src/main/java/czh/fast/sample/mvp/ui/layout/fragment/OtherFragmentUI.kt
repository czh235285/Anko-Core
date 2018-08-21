package czh.fast.sample.mvp.ui.layout.fragment

import android.graphics.Color
import android.view.Gravity
import android.view.View
import czh.fast.lib.utils.anko.itemLayout
import czh.fast.lib.widget.ItemBean
import czh.fast.lib.widget.ItemLayout
import czh.fast.sample.R
import czh.fast.sample.mvp.ui.fragment.OtherFragment
import org.jetbrains.anko.*

class OtherFragmentUI : AnkoComponent<OtherFragment> {
    lateinit var itemlayou: ItemLayout

    override fun createView(ui: AnkoContext<OtherFragment>): View = with(ui) {
        return verticalLayout {
            textView("通用item布局") {
                gravity = Gravity.CENTER
                setPadding(20, 20, 20, 20)
            }.lparams(matchParent) {
                topMargin = dip(20)
            }
            itemlayou = itemLayout {
                val items = arrayListOf<ItemBean>()
                items.add(ItemBean(R.mipmap.ic_launcher, "测试", hasTopLine = true))
                items.add(ItemBean(R.mipmap.ic_launcher, "测试"))
                items.add(ItemBean(R.mipmap.ic_launcher, "测试"))
                items.add(ItemBean(R.mipmap.ic_launcher, "测试", height = 2))

                mData = items
                //右边箭头
                defaultIcon = R.mipmap.ico_next
                //item高度
                itemHeight = dip(48)
                itemBgColor = Color.parseColor("#ffffff")
                //左边TextView
                leftTextColor = Color.parseColor("#333333")
                leftTextSize = dip(14).toFloat()
                leftPadding = 30
                leftDrawablePadding = 10
                //右边TextView
                rightTextColor = Color.parseColor("#666666")
                rightTextSize = dip(12).toFloat()
                rightPadding = 30
                rightDrawablePadding = 10
                //底部分割线
                lineColor = Color.parseColor("#e5e5e5")
                lineHeight = 2
                lineMarginLeft = 30
//            lineMarginRight=0
                setOnItemClickListener { view, position ->
                    ctx.toast(position.toString())
                }.create()

            }.lparams(matchParent) {
                topMargin = dip(20)
            }
        }
    }
}