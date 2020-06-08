package czh.fast.sample.widget

import android.content.Context
import android.graphics.Color
import android.os.SystemClock
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import czh.fast.lib.widget.Gloading.*
import org.jetbrains.anko.*


class StatusView : LinearLayout, View.OnClickListener {

    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER_HORIZONTAL
        setBackgroundColor(Color.parseColor("#ffffff"))
    }


    constructor(context: Context?, retryTask: Runnable?) : super(context) {
        mRetryTask = retryTask
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
            context,
            attrs,
            defStyleAttr
    )

    private var mRetryTask: Runnable? = null
    fun setStatus(status: Int) {
        var show = true
        var onClickListener: OnClickListener? = null
        when (status) {
            STATUS_LOAD_SUCCESS -> show = false
            STATUS_LOADING -> {
                removeAllViews()
                relativeLayout {
                    layoutParams = FrameLayout.LayoutParams(matchParent, matchParent)
                    loadingView {
                    }.lparams {
                        centerInParent()
                    }
                }
            }
            STATUS_LOAD_FAILED -> {
                removeAllViews()
                verticalLayout {
                    layoutParams = FrameLayout.LayoutParams(matchParent, matchParent)
                    textView {
                        text = "加载失败"
                    }.lparams {
                        gravity = Gravity.CENTER
                    }
                }
                onClickListener = this
            }
            STATUS_EMPTY_DATA -> {
                removeAllViews()
                verticalLayout {
                    layoutParams = FrameLayout.LayoutParams(matchParent, matchParent)
                    textView {
                        text = "暂无数据"
                    }.lparams {
                        gravity = Gravity.CENTER
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
            mRetryTask?.run()
            lastClickTime = SystemClock.elapsedRealtime()
        }
    }

}