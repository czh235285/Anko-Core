package c.core.widget.status


import android.annotation.SuppressLint
import android.content.Context
import android.os.SystemClock
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import c.core.R
import c.core.ex.*
import c.core.utils.color
import c.core.widget.loadingView
import c.core.widget.status.Gloading.*
import org.jetbrains.anko.*


@SuppressLint("ViewConstructor")
class StatusView : LinearLayout, View.OnClickListener {

    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER_HORIZONTAL
        backgroundColorResource = android.R.color.white
    }

    constructor(context: Context?, retryTask: Runnable?) : super(context) {
        mRetryTask = retryTask
    }

    private var mRetryTask: Runnable? = null

    fun setStatus(status: Int) {
        var show = true
        var onClickListener: OnClickListener? = null
        when (status) {
            STATUS_LOAD_SUCCESS -> show = false
            STATUS_LOADING -> {
                removeAllViews()
                relativeLayout {
                    layoutParams = LayoutParams(matchParent, matchParent)
                    loadingView {

                    }.lparams {
                        centerInParent()
                    }
                }
            }
            STATUS_LOAD_FAILED -> {
                removeAllViews()
                verticalLayout {
                    layoutParams = LayoutParams(matchParent, matchParent)
                    gravity = Gravity.CENTER
                    imageView(R.mipmap.ic_load_error).lparams { }
                    text(28, "#9D9D9D", "加载失败啦~").lparams {
                        topMargin = 28.px
                    }

                    text(24, "#ff0000", "重新加载") {
                        gravity = Gravity.CENTER
                        buildShape {
                            shapeRadius {
                                radius = 4.pxf
                            }
                            shapeStroke {
                                width = 2
                                color = "#ff0000".color
                            }
                        }
                    }.lparams(150.px, 60.px) {
                        topMargin = 28.px
                    }
                }
                onClickListener = this
            }
            STATUS_EMPTY_DATA -> {
                removeAllViews()
                verticalLayout {
                    layoutParams = LayoutParams(matchParent, matchParent)
                    gravity = Gravity.CENTER
                    imageView(R.mipmap.ic_load_empty).lparams { }
                    text(28, "#9D9D9D", "没有找到相关内容").lparams {
                        topMargin = 28.px
                    }
                }

            }
            else -> {
            }
        }
        setOnClickListener(onClickListener)
        visibility = if (show) View.VISIBLE else View.GONE
    }

    private var lastClickTime = 0L

    override fun onClick(v: View) {
        val diff = SystemClock.elapsedRealtime() - lastClickTime
        if (diff > 1000) {
            setStatus(STATUS_LOADING)
            mRetryTask?.run()
            lastClickTime = SystemClock.elapsedRealtime()
        }
    }

}