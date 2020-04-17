package czh.fast.sample.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import czh.fast.lib.widget.Gloading.*
import org.jetbrains.anko.*


class StatusView : LinearLayout, View.OnClickListener {
    lateinit var tv: TextView

    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER_HORIZONTAL
        addView(context.UI {
            relativeLayout {
                layoutParams = FrameLayout.LayoutParams(matchParent, matchParent)

                tv = textView {
                    backgroundColor = Color.parseColor("#ff0000")
                    text = "空布局"
                    textColor = Color.parseColor("#fff000")
                }.lparams(matchParent, matchParent)
            }

        }.view)
        setBackgroundColor(-0xf0f10)
    }


    constructor(context: Context?, retryTask: Runnable?) : super(context) {
        mRetryTask = retryTask
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var mRetryTask: Runnable? = null
    fun setStatus(status: Int) {
        var show = true
        var str = ""
        var onClickListener: OnClickListener? = null
        when (status) {
            STATUS_LOAD_SUCCESS -> show = false
            STATUS_LOADING -> str = "加载中"
            STATUS_LOAD_FAILED -> {
                "加载失败"
                onClickListener = this
            }
            STATUS_EMPTY_DATA -> {
                str = "空布局"
            }
            else -> {
            }
        }
        setOnClickListener(onClickListener)
        tv.text = str
        visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun onClick(v: View?) {
        mRetryTask?.run()
    }

}