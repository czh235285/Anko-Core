package c.core.widget.toolbar

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.view.ViewManager
import android.widget.FrameLayout
import c.core.ex.pSize
import c.core.ex.px
import c.core.ex.visible
import c.core.utils.throttleClick
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.textColor


inline fun ViewManager.ankoToolBar(
    theme: Int = 0,
    init: AnkoToolBar.() -> Unit
): AnkoToolBar {
    return ankoView({
        AnkoToolBar(it).apply {
            layoutParams = ViewGroup.LayoutParams(matchParent, 88.px)
        }
    }, theme, init)
}

class AnkoToolBar : FrameLayout {
    val ui by lazy { ToolbarUI() }

    constructor(context: Context) : super(context) {
        setBackgroundColor(Color.parseColor("#ffffff"))
        addView(ui.createView(AnkoContext.create(context)))
    }


    /**
     * 获取title
     */
    val tvTitle by lazy { ui.tvTitle }

    /**
     * 获取tvRight
     */
    val tvRight by lazy { ui.tvRight }

    /**
     * 获取ivRight
     */
    val ivRight by lazy { ui.ivRight }


    /**
     * 背景色
     */
    var backgroundColor: Int? = Color.parseColor("#ffffff")
        set(value) {
            value?.let {
                setBackgroundColor(it)
            }
            field = value
        }

    /**
     * 返回按钮Icon
     */
    var navigationIcon: Int? = null
        set(value) {
            value?.let {
                ui.ivBack.setImageResource(it)
            }
            field = value
        }

    /**
     * 返回按钮Icon
     */
    var rightIcon: Int? = null
        set(value) {
            value?.let {
                ui.ivRight.setImageResource(it)
                ui.ivRight.visible()
            }
            field = value
        }

    /**
     * 是否隐藏返回按钮Icon
     */
    var hideNavigation: Boolean = false
        set(value) {
            ui.ivBack.visibility = if (value) View.GONE else View.VISIBLE
            field = value
        }

    /**
     * 隐藏返回按钮Icon
     */
    fun hideNavigation() {
        hideNavigation = true
    }

    /**
     * 标题文字
     */
    var title: String = ""
        set(value) {
            ui.tvTitle.text = value
            field = value
        }

    /**
     * 标题颜色
     */
    var titleColor = Color.parseColor("#333333")
        set(value) {
            ui.tvTitle.textColor = value
            field = value
        }

    /**
     * 标题大小
     */
    var titleSize = 20f
        set(value) {
            ui.tvTitle.textSize = value
            field = value
        }

    var action: () -> Unit = {}
        set(value) {
            ui.ivBack.throttleClick {
                field.invoke()
            }
            field = value
        }

    /**
     * 右边文字
     */
    var rightText: String = ""
        set(value) {
            ui.tvRight.apply {
                text = value
                visibility = View.VISIBLE
            }
            field = value
        }

    /**
     * 右边文字颜色
     */
    var rightTextColor = Color.parseColor("#333333")
        set(value) {
            ui.tvRight.textColor = value
            field = value
        }

    /**
     * 右边文字大小
     */
    var rightTextSize = 20
        set(value) {
            ui.tvRight.pSize(value)
            field = value
        }
}