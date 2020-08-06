package c.core.sample.ui.layout

import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import c.core.ex.*
import c.core.ex.anko.horizontalLayout
import c.core.sample.ui.activity.EditAniTestActivity
import c.core.utils.changeAlpha
import c.core.utils.color
import c.core.widget.toolbar.AnkoToolBar
import c.core.widget.toolbar.ankoToolBar
import org.jetbrains.anko.*


/**
 *  author : czh
 *  date : 2020-8-6
 *  description : 
 */
class EditAniTestActivityUI : AnkoComponent<EditAniTestActivity> {
    lateinit var bar: AnkoToolBar
    lateinit var hl: LinearLayout
    lateinit var etCancel: TextView
    lateinit var llcontent: LinearLayout
    lateinit var et: EditText

    override fun createView(ui: AnkoContext<EditAniTestActivity>): View = with(ui) {
        verticalLayout {
            fitsSystemWindows = true
            hl = verticalLayout {

                bar = ankoToolBar {
                    title = "标题栏"
                }
                linearLayout {
                    orientation = LinearLayout.HORIZONTAL
                    et = editText {
                        this.commonShape("#fafafa".color, 40.pxf)

                    }.lparams(690.px, 80.px) {
                        horizontalMargin = 30.px
                        bottomMargin = 10.px
                    }

                    etCancel = text(30, content = "取消") {
                        gravity = Gravity.CENTER
                    }.lparams(80.px, 80.px)
                }.lparams(750.px, -2)
            }.lparams(-1)

            llcontent = linearLayout {
                invisible()
                backgroundColor = "#000000".color.changeAlpha(0.5f)
            }.lparams(-1, -1)
        }
    }
}