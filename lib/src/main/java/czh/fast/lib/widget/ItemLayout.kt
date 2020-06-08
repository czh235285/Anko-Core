package czh.fast.lib.widget

import android.content.Context
import android.graphics.Color
import androidx.core.content.ContextCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import czh.fast.lib.R
import czh.fast.lib.bean.ItemBean
import kotlinx.android.synthetic.main.item.view.*


class ItemLayout : LinearLayout {
    private var mContext: Context
    private var onItemClickListener: OnItemClickListener? = null
    private val mViewList = arrayListOf<View>()
    private val mLineViewList = arrayListOf<View>()

    var defaultIcon: Int = R.mipmap.ico_next

    var mData: List<ItemBean>? = null
    var itemHeight = 96
    var itemBgColor = Color.parseColor("#ffffff")

    var iconSize = 60

    var leftTextSize = 28f
    var leftTextColor = Color.parseColor("#333333")
    var leftPadding = 30
    var leftDrawablePadding = 10

    var rightTextSize = 28f
    var rightTextColor = Color.parseColor("#333333")
    var rightPadding = 30
    var rightDrawablePadding = 10

    var lineHeight = 1
    var lineColor = Color.parseColor("#e5e5e5")
    var lineMarginLeft = 30
    var lineMarginRight = 0

    constructor(context: Context) : this(context, null, 0) {
        mContext = context
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        mContext = context
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mContext = context
        orientation = VERTICAL
        context.theme.obtainStyledAttributes(attrs, R.styleable.ItemLayout, defStyleAttr, 0).let {
            (0 until it.indexCount).forEach { index ->
                val type = it.getIndex(index)
                when (type) {
                    R.styleable.ItemLayout_itemHeight -> itemHeight = it.getDimensionPixelOffset(type, 48)
                    R.styleable.ItemLayout_itemBgColor -> itemBgColor = it.getColor(type, Color.parseColor("#ffffff"))

                    R.styleable.ItemLayout_iconSize -> iconSize = it.getDimensionPixelSize(type, 60)
                    R.styleable.ItemLayout_leftTextSize -> leftTextSize = it.getDimension(type, 28f)
                    R.styleable.ItemLayout_leftTextColor -> leftTextColor = it.getColor(type, Color.parseColor("#333333"))
                    R.styleable.ItemLayout_leftPadding -> leftPadding = it.getDimensionPixelOffset(type, 0)
                    R.styleable.ItemLayout_leftDrawablePadding -> leftDrawablePadding = it.getDimensionPixelOffset(type, 0)

                    R.styleable.ItemLayout_rightTextSize -> rightTextSize = it.getDimension(type, 28f)
                    R.styleable.ItemLayout_rightTextColor -> rightTextColor = it.getColor(type, Color.parseColor("#333333"))
                    R.styleable.ItemLayout_rightPadding -> rightPadding = it.getDimensionPixelOffset(type, 0)
                    R.styleable.ItemLayout_rightDrawablePadding -> rightDrawablePadding = it.getDimensionPixelOffset(type, 0)

                    R.styleable.ItemLayout_lineHeight -> lineHeight = it.getDimensionPixelOffset(type, 1)
                    R.styleable.ItemLayout_lineColor -> lineColor = it.getColor(type, Color.parseColor("#e5e5e5"))
                    R.styleable.ItemLayout_lineMarginLeft -> lineMarginLeft = it.getDimensionPixelOffset(type, 0)
                    R.styleable.ItemLayout_lineMarginRight -> lineMarginRight = it.getDimensionPixelOffset(type, 0)
                }
            }
            it.recycle()
        }
    }


    fun getView(position: Int): View {
        return mViewList[position]
    }

    fun getLineView(position: Int): View {
        return mLineViewList[position]
    }

    fun getLeftTextView(position: Int): TextView {
        return mViewList[position].leftTextView
    }

    fun getRightTextView(position: Int): TextView {
        return mViewList[position].rightTextView
    }


    fun replaceRightText(position: Int, text: String) {
        getRightTextView(position).text = text
    }

    fun replaceLeftText(position: Int, text: String) {
        getLeftTextView(position).text = text
    }

    fun create() {
        mData?.forEachIndexed { position, it ->
            val view = LayoutInflater.from(mContext).inflate(R.layout.item, null).apply {
                itemLayout.setBackgroundColor(itemBgColor)
                itemLayout.layoutParams = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, itemHeight)
                leftTextView.apply {
                    textSize = px2dip(mContext, leftTextSize).toFloat()
                    setTextColor(leftTextColor)
                    setPadding(leftPadding, 0, 0, 0)
                    text = it.leftText
                    it.leftIcon?.let {
                        compoundDrawablePadding = leftDrawablePadding
                        setCompoundDrawables(ContextCompat.getDrawable(mContext, it)?.apply {
                            setBounds(0, 0, iconSize, iconSize)
                        }, null, null, null)
                    }
                }

                rightTextView.apply {
                    textSize = px2dip(mContext, rightTextSize).toFloat()
                    it.rightText?.let { text = it }
                    setPadding(0, 0, rightPadding, 0)
                    setTextColor(rightTextColor)
                    setCompoundDrawables(null, null, ContextCompat.getDrawable(mContext, defaultIcon)?.apply {
                        compoundDrawablePadding = rightDrawablePadding
                        setBounds(0, 0, minimumWidth, minimumHeight)
                    }, null)
                }
            }

            if (it.hasTopLine) {
                addView(createLineView(lineHeight))
            }

            addView(view)
            mViewList.add(view)


            createLineView(it.height, it.marginBottom).let {
                addView(it)
                mLineViewList.add(it)
            }

            it.marginBottom?.let {
                addView(createLineView(lineHeight))
            }

            view.setOnClickListener {
                onItemClickListener?.onItemClick(view, position)
            }
        }
    }

    /**
     * 创建线
     * @param height
     * @return View
     */
    private fun createLineView(height: Int? = null, marginBottom: Int? = null): View {
        return View(mContext).apply {
            setBackgroundColor(lineColor)
            val lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height
                    ?: lineHeight)
            if (height == null && marginBottom == null) {
                lp.leftMargin = lineMarginLeft
                lp.rightMargin = lineMarginRight
            }
            marginBottom?.let {
                lp.bottomMargin = it
            }
            layoutParams = lp
        }
    }

    /**
     * convert dp to its equivalent px
     *
     * 将dp转换为与之相等的px
     */
    fun dp2px(dipValue: Float): Int {
        val scale = mContext.resources.displayMetrics.density
        return (dipValue * scale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * item点击事件监听
     */
    internal interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }


    fun setOnItemClickListener(action: (view: View, position: Int) -> Unit): ItemLayout {
        onItemClickListener = object : OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                action(view, position)
            }
        }
        return this
    }
}