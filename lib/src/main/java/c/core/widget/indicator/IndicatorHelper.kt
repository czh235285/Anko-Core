package c.core.widget.indicator


import android.content.Context
import android.graphics.Typeface
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewManager
import android.widget.FrameLayout
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import c.core.ex.px
import c.core.ex.pxf
import c.core.utils.color
import c.core.utils.throttleClick
import net.lucode.hackware.magicindicator.FragmentContainerHelper
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.WrapPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView


/**
 * MagicIndicator扩展类
 * 扩展了3个常用样式，使用起来更方便一点。
 * 首页TAB，一般的TabLayout+viewpager样式，指示器为包住textView的圆角shape样式
 */
class IndicatorHelper {

    lateinit var mContext: Context
    var vp: ViewPager? = null
    var mic: MagicIndicator? = null
    var action: (it: Int) -> Unit? = {

    }


    internal var mTitles: Array<String>? = null
    internal var mIconUnSelectIds: IntArray? = null
    internal var mIconSelectIds: IntArray? = null
    internal var mIconWidth: Int = 40.px
    internal var mIconHeight: Int = 40.px
    internal var spacePadding: Int = 10.px


    internal var isHomeTab = false


    internal var isSelectBold = true
    internal var selectTextColor: Int = "#222222".color
    internal var unSelectTextColor: Int = "#999999".color
    internal var selectTextSize = 25.px
    internal var unSelectTextSize = 25.px


    internal var isUseIndicator = true
    internal var isUseShape = false
    internal var indicatorColor = "#FF444A".color
    internal var indicatorRoundRadius = 2.pxf
    internal var indicatorHeight = 4.pxf

    internal var titleViewHorizontalPadding: Int = 10
    internal var tabHorizontalPadding: Int = 10


    internal var xOffset: Float = 0f
    internal var yOffset: Float = 0f


    class Indicator {
        var isUseIndicator = true
        var isUseShape = false
        var color: Int = "#22BB62".color
        var height: Float = 4.pxf
        var radius: Float = 2.pxf
        var xOffset: Float = 0f
        var yOffset: Float = 0f
    }


    class Padding {
        var tabPadding: Int = 25.px
        var titlePadding: Int = 25.px
    }


    class Text {
        var isSelectBold = true
        var selectTextColor: Int = "#484848".color
        var unSelectTextColor: Int = "#848484".color
        var selectTextSize: Int = 25.px
        var unSelectTextSize: Int = 25.px
    }

    class HomeTab {
        var isSelectBold = false
        var selectTextColor: Int = "#484848".color
        var unSelectTextColor: Int = "#848484".color
        var selectTextSize: Int = 25.px
        var unSelectTextSize: Int = 25.px
        var mTitles: Array<String>? = null
        var mIconUnSelectIds: IntArray? = null
        var mIconSelectIds: IntArray? = null
        var mIconWidth: Int = 40.px
        var mIconHeight: Int = 40.px
        var spacePadding: Int = 10.px
    }


    fun create(): IndicatorHelper {
        mic?.setPadding(
            tabHorizontalPadding - titleViewHorizontalPadding, 0,
            tabHorizontalPadding - titleViewHorizontalPadding, 0
        )
        val mFragmentContainerHelper = FragmentContainerHelper(mic)

        val commonNavigator = CommonNavigator(mContext).apply {
            if (isHomeTab) {
                isAdjustMode = true
            }

            adapter = object : CommonNavigatorAdapter() {


                override fun getIndicator(context: Context?): IPagerIndicator? {
                    if (!isUseIndicator) {
                        return null
                    }

                    if (isUseShape) {
                        return WrapPagerIndicator(context).also {
                            it.fillColor = indicatorColor
                            it.roundRadius = indicatorRoundRadius
                            it.horizontalPadding = xOffset.toInt()
                            it.verticalPadding = yOffset.toInt()
                        }
                    }

                    return LinePagerIndicator(context).also {
                        it.mode = LinePagerIndicator.MODE_WRAP_CONTENT
                        it.lineHeight = indicatorHeight
                        it.roundRadius = indicatorRoundRadius
                        it.setColors(indicatorColor)
                        it.yOffset = yOffset
                    }

                }

                override fun getCount(): Int {
                    return mTitles?.size ?: 0
                }

                override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                    if (isHomeTab) {
                        return CommonPagerTitleView(context).also {
                            lateinit var icon: View
                            lateinit var tv: TextView
                            it.verticalLayout {
                                layoutParams = FrameLayout.LayoutParams(-1, -1)
                                gravity = Gravity.CENTER
                                icon = view {
                                    mIconUnSelectIds?.get(index)
                                        ?.let { backgroundResource = it }
                                }.lparams(mIconWidth, mIconHeight)
                                tv = textView {
                                    mTitles?.get(index)?.let { text = it }
                                    setTextSize(
                                        TypedValue.COMPLEX_UNIT_PX,
                                        unSelectTextSize.toFloat()
                                    )
                                    textColor = unSelectTextColor
                                }.lparams {
                                    topMargin = spacePadding
                                }
                            }

                            it.throttleClick {
                                vp?.setCurrentItem(index, false)
                            }
                            it.onPagerTitleChangeListener =
                                object : CommonPagerTitleView.OnPagerTitleChangeListener {
                                    override fun onDeselected(index: Int, totalCount: Int) {
                                        tv.setTextSize(
                                            TypedValue.COMPLEX_UNIT_PX,
                                            unSelectTextSize.toFloat()
                                        )
                                        tv.textColor = unSelectTextColor
                                        mIconUnSelectIds?.get(index)
                                            ?.let { icon.backgroundResource = it }

                                        if (isSelectBold) {
                                            tv.typeface = Typeface.defaultFromStyle(Typeface.NORMAL)
                                        }
                                    }

                                    override fun onSelected(index: Int, totalCount: Int) {
                                        tv.setTextSize(
                                            TypedValue.COMPLEX_UNIT_PX,
                                            selectTextSize.toFloat()
                                        )
                                        tv.textColor = selectTextColor
                                        mIconSelectIds?.get(index)
                                            ?.let { icon.backgroundResource = it }
                                        if (isSelectBold) {
                                            tv.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                                        }
                                    }

                                    override fun onLeave(
                                        index: Int,
                                        totalCount: Int,
                                        leavePercent: Float,
                                        leftToRight: Boolean
                                    ) {

                                    }

                                    override fun onEnter(
                                        index: Int,
                                        totalCount: Int,
                                        enterPercent: Float,
                                        leftToRight: Boolean
                                    ) {

                                    }

                                }

                        }
                    }


                    return ColorTransitionPagerTitleView(context, titleViewHorizontalPadding).also {
                        it.isSelectBold = isSelectBold
                        it.normalColor = unSelectTextColor
                        it.selectedColor = selectTextColor
                        it.setNormalTextSize(unSelectTextSize)
                        it.setSelectedTextSize(selectTextSize)
                        it.text = mTitles?.get(index)
                        it.setOnClickListener {
                            mFragmentContainerHelper.handlePageSelected(index)
                            vp?.setCurrentItem(index, false)
                            action.invoke(index)
                        }
                    }
                }

                override fun getTitleWeight(context: Context?, index: Int): Float {
                    return 1f
                }
            }


        }
        mic?.navigator = commonNavigator

        vp?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                mic?.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                mic?.onPageSelected(position)
                action.invoke(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                mic?.onPageScrollStateChanged(state)
            }
        })

        return this
    }
}

inline fun ViewManager.magicIndicator(
    theme: Int = 0,
    init: MagicIndicator.() -> Unit
): MagicIndicator {
    return ankoView({
        MagicIndicator(it)
    }, theme, init)
}


fun IndicatorHelper.configPadding(init: IndicatorHelper.Padding.() -> Unit) {
    IndicatorHelper.Padding().also {
        it.init()
        this.tabHorizontalPadding = it.tabPadding
        this.titleViewHorizontalPadding = it.titlePadding
    }
}

fun IndicatorHelper.configIndicator(init: IndicatorHelper.Indicator.() -> Unit) {
    IndicatorHelper.Indicator().also {
        it.init()
        this.isUseIndicator = it.isUseIndicator
        this.indicatorColor = it.color
        this.indicatorHeight = it.height
        this.indicatorRoundRadius = it.radius
        this.xOffset = it.xOffset
        this.yOffset = it.yOffset
        this.isUseShape = it.isUseShape
    }
}

fun IndicatorHelper.setData(value: Array<String>) {
    if (this.mTitles == null) {
        this.mTitles = value
    } else {
        this.mTitles = value
        this.mic?.navigator?.notifyDataSetChanged()
    }
}

fun IndicatorHelper.configText(init: IndicatorHelper.Text.() -> Unit) {
    IndicatorHelper.Text().also {
        it.init()
        this.isSelectBold = it.isSelectBold
        this.selectTextColor = it.selectTextColor
        this.unSelectTextColor = it.unSelectTextColor
        this.selectTextSize = it.selectTextSize
        this.unSelectTextSize = it.unSelectTextSize
    }
}

fun MagicIndicator.bind(
    vp: ViewPager,
    value: Array<String>,
    init: IndicatorHelper.() -> Unit
): IndicatorHelper {
    return IndicatorHelper().also {
        it.mContext = this.context
        it.mTitles = value
        it.mic = this
        it.vp = vp
        it.init()
        it.create()
    }
}

fun MagicIndicator.bind(vp: ViewPager, init: IndicatorHelper.HomeTab.() -> Unit) {
    IndicatorHelper().apply {
        this.mic = this@bind
        this.mContext = this@bind.context
        this.vp = vp
        this.isHomeTab = true
        this.isUseIndicator = false
        IndicatorHelper.HomeTab().also {
            it.init()
            this.isSelectBold = it.isSelectBold
            this.selectTextColor = it.selectTextColor
            this.unSelectTextColor = it.unSelectTextColor
            this.selectTextSize = it.selectTextSize
            this.unSelectTextSize = it.unSelectTextSize
            this.mTitles = it.mTitles
            this.mIconUnSelectIds = it.mIconUnSelectIds
            this.mIconSelectIds = it.mIconSelectIds
            this.mIconWidth = it.mIconWidth
            this.mIconHeight = it.mIconHeight
            this.spacePadding = it.spacePadding
        }
        create()
    }
}