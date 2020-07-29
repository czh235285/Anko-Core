package c.core.layout

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import c.core.ex.px
import c.core.ex.text
import org.jetbrains.anko.*

class LoadMoreViewUI : AnkoComponent<Context> {
    lateinit var loadCompleteView: TextView
    lateinit var loadEndView: TextView
    lateinit var loadFailView: TextView
    lateinit var loadingView: LinearLayout
    override fun createView(ui: AnkoContext<Context>) = with(ui) {
        frameLayout {
            lparams(-1, 88.px)
            loadingView = linearLayout {
                gravity = Gravity.CENTER
                progressBar {
                    indeterminateTintList = ColorStateList.valueOf(Color.parseColor("#22bb62"))
                }.lparams(40.px, 40.px)
                text(30, "#666666", "正在加载中...").lparams {
                    leftMargin = 10.px
                }
            }.lparams(-1, -1)

            loadFailView = text(30, "#666666", "加载失败,点击重试") {
                gravity = Gravity.CENTER
            }.lparams(-1, -1)

            loadCompleteView = text(30, "#666666", "点击加载更多") {
                gravity = Gravity.CENTER
            }.lparams(-1, -1)

            loadEndView = text(30, "#666666", "已经到底啦~") {
                gravity = Gravity.CENTER
            }.lparams(-1, -1)
        }
    }
}