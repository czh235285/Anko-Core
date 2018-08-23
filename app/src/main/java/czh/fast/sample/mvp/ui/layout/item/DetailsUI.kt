package czh.fast.sample.mvp.ui.layout.item

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.pawegio.kandroid.dp
import com.youth.banner.Banner
import com.zhy.view.flowlayout.TagFlowLayout
import czh.fast.lib.utils.anko.circleImageView
import czh.fast.lib.utils.anko.tagFlowLayout
import czh.fast.lib.utils.setShape
import czh.fast.sample.utils.banners
import org.jetbrains.anko.*

class DetailsUI : AnkoComponent<Context> {

    lateinit var civ: SimpleDraweeView

    lateinit var name: TextView

    lateinit var tvTitle: TextView

    lateinit var tvContent: TextView

    lateinit var tvTime: TextView

    lateinit var tvNumber: TextView

    lateinit var taglayout: TagFlowLayout

    @SuppressLint("ResourceType")
    override fun createView(ui: AnkoContext<Context>) = with(ui) {
        verticalLayout {
            lparams(matchParent, wrapContent)
            horizontalPadding = dip(16)
            relativeLayout {
                civ = circleImageView {
                    id = 1
                }.lparams(dip(30), dip(30)) {
                    centerVertically()
                }

                name = textView("名字") {
                    textSize = 15f
                    textColor = Color.parseColor("#333333")
                }.lparams {
                    leftMargin = dip(8)
                    rightOf(1)
                    centerVertically()
                }
                textView("+关注") {
                    setPadding(16, 8, 16, 8)
                    setShape("#fed500", 16f)
                }.lparams {
                    alignParentRight()
                    centerVertically()
                }
            }.lparams(matchParent, dip(40))
            taglayout = tagFlowLayout { }
            tvTitle = textView {
                topPadding = dip(20)
                textSize = 15f
                typeface = Typeface.DEFAULT_BOLD
                textColor = Color.parseColor("#333333")
            }
            tvContent = textView {
                topPadding = dip(20)
                textSize = 15f
                textColor = Color.parseColor("#333333")
            }
            relativeLayout {
                verticalPadding = dip(16)
                tvTime = textView {
                    textSize = 14f
                    textColor = Color.parseColor("#666666")
                }
                tvNumber = textView {
                    textSize = 14f
                    textColor = Color.parseColor("#ff0000")
                }.lparams { alignParentRight() }
            }
        }
    }
}

