package czh.fast.sample.widget

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.FrameLayout
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.textColor

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
        }
    /**
     * 返回按钮Icon
     */
    var navigationIcon: Int? = null
        set(value) {
            value?.let {
                ui.ivBack.setImageResource(it)
            }
        }
    /**
     * 是否隐藏返回按钮Icon
     */
    var hideNavigation: Boolean = false
        set(value) {
            ui.ivBack.visibility = if (value) View.GONE else View.VISIBLE
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
        }
    /**
     * 标题颜色
     */
    var titleColor = Color.parseColor("#333333")
        set(value) {
            ui.tvTitle.textColor = value
        }
    /**
     * 标题大小
     */
    var titleSize = 20f
        set(value) {
            ui.tvTitle.textSize = value
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
        }
    /**
     * 右边文字颜色
     */
    var rightTextColor = Color.parseColor("#333333")
        set(value) {
            ui.tvRight.textColor = value
        }
    /**
     * 右边文字大小
     */
    var rightTextSize = 14f
        set(value) {
            ui.tvRight.textSize = value
        }
}