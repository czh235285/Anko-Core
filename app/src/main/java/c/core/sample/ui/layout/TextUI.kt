package c.core.sample.ui.layout

import android.content.Context
import android.widget.TextView
import c.core.ex.*
import c.core.ex.anko.appBarLayout
import c.core.utils.color
import org.jetbrains.anko.*

class TextUI : AnkoComponent<Context> {
    lateinit var tvContent: TextView

    override fun createView(ui: AnkoContext<Context>) = with(ui) {
        verticalLayout {
            lparams(-1)
            tvContent = text(25, "#262626") {
                horizontalPadding = 30.px
                verticalPadding = 30.px
appBarLayout(){

}
                buildShape {
                    shapeStroke {
                        width = 2
                        color = "#ff0000".color
                    }
                    shapeRadius {
                        topLeft = 10.pxf
                        topRight = 20.pxf
                        bottomRight = 30.pxf
                        bottomLeft = 40.pxf
                    }
                    shapeGradient {
                        startColor = "#ffffff".color
                        endColor = "#333333".color
                    }
                }


            }.lparams(-1) {
                margin = 20.px
            }
        }
    }
}