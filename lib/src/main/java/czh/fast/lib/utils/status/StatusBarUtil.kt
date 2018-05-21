package czh.fast.lib.utils.status

import android.view.View
import android.view.ViewGroup
import czh.fast.lib.R

/**
 * Created by Jaeger on 16/2/14.
 *
 *
 * Email: chjie.jaeger@gmail.com
 * GitHub: https://github.com/laobie
 */
object StatusBarUtil {

    val DEFAULT_STATUS_BAR_ALPHA = 112
    private val FAKE_STATUS_BAR_VIEW_ID = R.id.statusbarutil_fake_status_bar_view
    private val FAKE_TRANSLUCENT_VIEW_ID = R.id.statusbarutil_translucent_view
    private val TAG_KEY_HAVE_SET_OFFSET = -123

    /**
     * 设置状态栏颜色

     * @param activity       需要设置的activity
     * *
     * @param color          状态栏颜色值
     * *
     * @param statusBarAlpha 状态栏透明度
     */

    @JvmOverloads fun setColor(activity: android.app.Activity, @android.support.annotation.ColorInt color: Int, @android.support.annotation.IntRange(from = 0, to = 255) statusBarAlpha: Int = DEFAULT_STATUS_BAR_ALPHA) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            activity.window.addFlags(android.view.WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            activity.window.clearFlags(android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            activity.window.statusBarColor = calculateStatusColor(color, statusBarAlpha)
        } else if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            activity.window.addFlags(android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            val decorView = activity.window.decorView as android.view.ViewGroup
            val fakeStatusBarView = decorView.findViewById<View>(FAKE_STATUS_BAR_VIEW_ID)
            if (fakeStatusBarView != null) {
                if (fakeStatusBarView.visibility == android.view.View.GONE) {
                    fakeStatusBarView.visibility = android.view.View.VISIBLE
                }
                fakeStatusBarView.setBackgroundColor(calculateStatusColor(color, statusBarAlpha))
            } else {
                decorView.addView(createStatusBarView(activity, color, statusBarAlpha))
            }
            setRootView(activity)
        }
    }

    /**
     * 为滑动返回界面设置状态栏颜色

     * @param activity       需要设置的activity
     * *
     * @param color          状态栏颜色值
     * *
     * @param statusBarAlpha 状态栏透明度
     */
    @JvmOverloads fun setColorForSwipeBack(activity: android.app.Activity, @android.support.annotation.ColorInt color: Int,
                                           @android.support.annotation.IntRange(from = 0, to = 255) statusBarAlpha: Int = DEFAULT_STATUS_BAR_ALPHA) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {

            val contentView = activity.findViewById<View>(android.R.id.content) as android.view.ViewGroup
            val rootView = contentView.getChildAt(0)
            val statusBarHeight = getStatusBarHeight(activity)
            if (rootView != null && rootView is android.support.design.widget.CoordinatorLayout) {
                val coordinatorLayout = rootView
                if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {
                    coordinatorLayout.fitsSystemWindows = false
                    contentView.setBackgroundColor(calculateStatusColor(color, statusBarAlpha))
                    val isNeedRequestLayout = contentView.paddingTop < statusBarHeight
                    if (isNeedRequestLayout) {
                        contentView.setPadding(0, statusBarHeight, 0, 0)
                        coordinatorLayout.post { coordinatorLayout.requestLayout() }
                    }
                } else {
                    coordinatorLayout.setStatusBarBackgroundColor(calculateStatusColor(color, statusBarAlpha))
                }
            } else {
                contentView.setPadding(0, statusBarHeight, 0, 0)
                contentView.setBackgroundColor(calculateStatusColor(color, statusBarAlpha))
            }
            setTransparentForWindow(activity)
        }
    }

    /**
     * 设置状态栏纯色 不加半透明效果

     * @param activity 需要设置的 activity
     * *
     * @param color    状态栏颜色值
     */
    fun setColorNoTranslucent(activity: android.app.Activity, @android.support.annotation.ColorInt color: Int) {
        setColor(activity, color, 0)
    }

    /**
     * 设置状态栏颜色(5.0以下无半透明效果,不建议使用)

     * @param activity 需要设置的 activity
     * *
     * @param color    状态栏颜色值
     */
    @Deprecated("")
    fun setColorDiff(activity: android.app.Activity, @android.support.annotation.ColorInt color: Int) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.KITKAT) {
            return
        }
        transparentStatusBar(activity)
        val contentView = activity.findViewById<View>(android.R.id.content) as android.view.ViewGroup
        // 移除半透明矩形,以免叠加
        val fakeStatusBarView = contentView.findViewById<View>(FAKE_STATUS_BAR_VIEW_ID)
        if (fakeStatusBarView != null) {
            if (fakeStatusBarView.visibility == android.view.View.GONE) {
                fakeStatusBarView.visibility = android.view.View.VISIBLE
            }
            fakeStatusBarView.setBackgroundColor(color)
        } else {
            contentView.addView(createStatusBarView(activity, color))
        }
        setRootView(activity)
    }

    /**
     * 使状态栏半透明
     *
     *
     * 适用于图片作为背景的界面,此时需要图片填充到状态栏

     * @param activity       需要设置的activity
     * *
     * @param statusBarAlpha 状态栏透明度
     */
    @JvmOverloads fun setTranslucent(activity: android.app.Activity, @android.support.annotation.IntRange(from = 0, to = 255) statusBarAlpha: Int = DEFAULT_STATUS_BAR_ALPHA) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.KITKAT) {
            return
        }
        setTransparent(activity)
        addTranslucentView(activity, statusBarAlpha)
    }

    /**
     * 针对根布局是 CoordinatorLayout, 使状态栏半透明
     *
     *
     * 适用于图片作为背景的界面,此时需要图片填充到状态栏

     * @param activity       需要设置的activity
     * *
     * @param statusBarAlpha 状态栏透明度
     */
    fun setTranslucentForCoordinatorLayout(activity: android.app.Activity, @android.support.annotation.IntRange(from = 0, to = 255) statusBarAlpha: Int) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.KITKAT) {
            return
        }
        transparentStatusBar(activity)
        addTranslucentView(activity, statusBarAlpha)
    }

    /**
     * 设置状态栏全透明

     * @param activity 需要设置的activity
     */
    fun setTransparent(activity: android.app.Activity) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.KITKAT) {
            return
        }
        transparentStatusBar(activity)
        setRootView(activity)
    }

    /**
     * 使状态栏透明(5.0以上半透明效果,不建议使用)
     *
     *
     * 适用于图片作为背景的界面,此时需要图片填充到状态栏

     * @param activity 需要设置的activity
     */
    @Deprecated("")
    fun setTranslucentDiff(activity: android.app.Activity) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            // 设置状态栏透明
            activity.window.addFlags(android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            setRootView(activity)
        }
    }

    /**
     * 为DrawerLayout 布局设置状态栏颜色,纯色

     * @param activity     需要设置的activity
     * *
     * @param drawerLayout DrawerLayout
     * *
     * @param color        状态栏颜色值
     */
    fun setColorNoTranslucentForDrawerLayout(activity: android.app.Activity, drawerLayout: android.support.v4.widget.DrawerLayout, @android.support.annotation.ColorInt color: Int) {
        setColorForDrawerLayout(activity, drawerLayout, color, 0)
    }

    /**
     * 为DrawerLayout 布局设置状态栏变色

     * @param activity       需要设置的activity
     * *
     * @param drawerLayout   DrawerLayout
     * *
     * @param color          状态栏颜色值
     * *
     * @param statusBarAlpha 状态栏透明度
     */
    @JvmOverloads fun setColorForDrawerLayout(activity: android.app.Activity, drawerLayout: android.support.v4.widget.DrawerLayout, @android.support.annotation.ColorInt color: Int,
                                              @android.support.annotation.IntRange(from = 0, to = 255) statusBarAlpha: Int = DEFAULT_STATUS_BAR_ALPHA) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.KITKAT) {
            return
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            activity.window.addFlags(android.view.WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            activity.window.clearFlags(android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            activity.window.statusBarColor = android.graphics.Color.TRANSPARENT
        } else {
            activity.window.addFlags(android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        // 生成一个状态栏大小的矩形
        // 添加 statusBarView 到布局中
        val contentLayout = drawerLayout.getChildAt(0) as android.view.ViewGroup
        val fakeStatusBarView = contentLayout.findViewById<View>(FAKE_STATUS_BAR_VIEW_ID)
        if (fakeStatusBarView != null) {
            if (fakeStatusBarView.visibility == android.view.View.GONE) {
                fakeStatusBarView.visibility = android.view.View.VISIBLE
            }
            fakeStatusBarView.setBackgroundColor(color)
        } else {
            contentLayout.addView(createStatusBarView(activity, color), 0)
        }
        // 内容布局不是 LinearLayout 时,设置padding top
        if (contentLayout !is android.widget.LinearLayout && contentLayout.getChildAt(1) != null) {
            contentLayout.getChildAt(1)
                    .setPadding(contentLayout.paddingLeft, getStatusBarHeight(activity) + contentLayout.paddingTop,
                            contentLayout.paddingRight, contentLayout.paddingBottom)
        }
        // 设置属性
        setDrawerLayoutProperty(drawerLayout, contentLayout)
        addTranslucentView(activity, statusBarAlpha)
    }

    /**
     * 设置 DrawerLayout 属性

     * @param drawerLayout              DrawerLayout
     * *
     * @param drawerLayoutContentLayout DrawerLayout 的内容布局
     */
    private fun setDrawerLayoutProperty(drawerLayout: android.support.v4.widget.DrawerLayout, drawerLayoutContentLayout: android.view.ViewGroup) {
        val drawer = drawerLayout.getChildAt(1) as android.view.ViewGroup
        drawerLayout.fitsSystemWindows = false
        drawerLayoutContentLayout.fitsSystemWindows = false
        drawerLayoutContentLayout.clipToPadding = true
        drawer.fitsSystemWindows = false
    }

    /**
     * 为DrawerLayout 布局设置状态栏变色(5.0以下无半透明效果,不建议使用)

     * @param activity     需要设置的activity
     * *
     * @param drawerLayout DrawerLayout
     * *
     * @param color        状态栏颜色值
     */
    @Deprecated("")
    fun setColorForDrawerLayoutDiff(activity: android.app.Activity, drawerLayout: android.support.v4.widget.DrawerLayout, @android.support.annotation.ColorInt color: Int) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            activity.window.addFlags(android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            // 生成一个状态栏大小的矩形
            val contentLayout = drawerLayout.getChildAt(0) as android.view.ViewGroup
            val fakeStatusBarView = contentLayout.findViewById<View>(FAKE_STATUS_BAR_VIEW_ID)
            if (fakeStatusBarView != null) {
                if (fakeStatusBarView.visibility == android.view.View.GONE) {
                    fakeStatusBarView.visibility = android.view.View.VISIBLE
                }
                fakeStatusBarView.setBackgroundColor(calculateStatusColor(color, DEFAULT_STATUS_BAR_ALPHA))
            } else {
                // 添加 statusBarView 到布局中
                contentLayout.addView(createStatusBarView(activity, color), 0)
            }
            // 内容布局不是 LinearLayout 时,设置padding top
            if (contentLayout !is android.widget.LinearLayout && contentLayout.getChildAt(1) != null) {
                contentLayout.getChildAt(1).setPadding(0, getStatusBarHeight(activity), 0, 0)
            }
            // 设置属性
            setDrawerLayoutProperty(drawerLayout, contentLayout)
        }
    }

    /**
     * 为 DrawerLayout 布局设置状态栏透明

     * @param activity     需要设置的activity
     * *
     * @param drawerLayout DrawerLayout
     */
    @JvmOverloads fun setTranslucentForDrawerLayout(activity: android.app.Activity, drawerLayout: android.support.v4.widget.DrawerLayout,
                                                    @android.support.annotation.IntRange(from = 0, to = 255) statusBarAlpha: Int = DEFAULT_STATUS_BAR_ALPHA) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.KITKAT) {
            return
        }
        setTransparentForDrawerLayout(activity, drawerLayout)
        addTranslucentView(activity, statusBarAlpha)
    }

    /**
     * 为 DrawerLayout 布局设置状态栏透明

     * @param activity     需要设置的activity
     * *
     * @param drawerLayout DrawerLayout
     */
    fun setTransparentForDrawerLayout(activity: android.app.Activity, drawerLayout: android.support.v4.widget.DrawerLayout) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.KITKAT) {
            return
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            activity.window.addFlags(android.view.WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            activity.window.clearFlags(android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            activity.window.statusBarColor = android.graphics.Color.TRANSPARENT
        } else {
            activity.window.addFlags(android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }

        val contentLayout = drawerLayout.getChildAt(0) as android.view.ViewGroup
        // 内容布局不是 LinearLayout 时,设置padding top
        if (contentLayout !is android.widget.LinearLayout && contentLayout.getChildAt(1) != null) {
            contentLayout.getChildAt(1).setPadding(0, getStatusBarHeight(activity), 0, 0)
        }

        // 设置属性
        setDrawerLayoutProperty(drawerLayout, contentLayout)
    }

    /**
     * 为 DrawerLayout 布局设置状态栏透明(5.0以上半透明效果,不建议使用)

     * @param activity     需要设置的activity
     * *
     * @param drawerLayout DrawerLayout
     */
    @Deprecated("")
    fun setTranslucentForDrawerLayoutDiff(activity: android.app.Activity, drawerLayout: android.support.v4.widget.DrawerLayout) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            // 设置状态栏透明
            activity.window.addFlags(android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            // 设置内容布局属性
            val contentLayout = drawerLayout.getChildAt(0) as android.view.ViewGroup
            contentLayout.fitsSystemWindows = true
            contentLayout.clipToPadding = true
            // 设置抽屉布局属性
            val vg = drawerLayout.getChildAt(1) as android.view.ViewGroup
            vg.fitsSystemWindows = false
            // 设置 DrawerLayout 属性
            drawerLayout.fitsSystemWindows = false
        }
    }

    /**
     * 为头部是 ImageView 的界面设置状态栏全透明

     * @param activity       需要设置的activity
     * *
     * @param needOffsetView 需要向下偏移的 View
     */
    fun setTransparentForImageView(activity: android.app.Activity, needOffsetView: android.view.View) {
        setTranslucentForImageView(activity, 0, needOffsetView)
    }

    /**
     * 为头部是 ImageView 的界面设置状态栏透明(使用默认透明度)

     * @param activity       需要设置的activity
     * *
     * @param needOffsetView 需要向下偏移的 View
     */
    fun setTranslucentForImageView(activity: android.app.Activity, needOffsetView: android.view.View) {
        setTranslucentForImageView(activity, DEFAULT_STATUS_BAR_ALPHA, needOffsetView)
    }

    /**
     * 为头部是 ImageView 的界面设置状态栏透明

     * @param activity       需要设置的activity
     * *
     * @param statusBarAlpha 状态栏透明度
     * *
     * @param needOffsetView 需要向下偏移的 View
     */
    fun setTranslucentForImageView(activity: android.app.Activity, @android.support.annotation.IntRange(from = 0, to = 255) statusBarAlpha: Int,
                                   needOffsetView: android.view.View?) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.KITKAT) {
            return
        }
        setTransparentForWindow(activity)
        addTranslucentView(activity, statusBarAlpha)
        if (needOffsetView != null) {
            val haveSetOffset = needOffsetView.getTag(TAG_KEY_HAVE_SET_OFFSET)
            if (haveSetOffset != null && haveSetOffset as Boolean) {
                return
            }
            val layoutParams = needOffsetView.layoutParams as android.view.ViewGroup.MarginLayoutParams
            layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin + getStatusBarHeight(activity),
                    layoutParams.rightMargin, layoutParams.bottomMargin)
            needOffsetView.setTag(TAG_KEY_HAVE_SET_OFFSET, true)
        }
    }

    /**
     * 为 fragment 头部是 ImageView 的设置状态栏透明

     * @param activity       fragment 对应的 activity
     * *
     * @param needOffsetView 需要向下偏移的 View
     */
    fun setTranslucentForImageViewInFragment(activity: android.app.Activity, needOffsetView: android.view.View) {
        setTranslucentForImageViewInFragment(activity, DEFAULT_STATUS_BAR_ALPHA, needOffsetView)
    }

    /**
     * 为 fragment 头部是 ImageView 的设置状态栏透明

     * @param activity       fragment 对应的 activity
     * *
     * @param needOffsetView 需要向下偏移的 View
     */
    fun setTransparentForImageViewInFragment(activity: android.app.Activity, needOffsetView: android.view.View) {
        setTranslucentForImageViewInFragment(activity, 0, needOffsetView)
    }

    /**
     * 为 fragment 头部是 ImageView 的设置状态栏透明

     * @param activity       fragment 对应的 activity
     * *
     * @param statusBarAlpha 状态栏透明度
     * *
     * @param needOffsetView 需要向下偏移的 View
     */
    fun setTranslucentForImageViewInFragment(activity: android.app.Activity, @android.support.annotation.IntRange(from = 0, to = 255) statusBarAlpha: Int,
                                             needOffsetView: android.view.View) {
        setTranslucentForImageView(activity, statusBarAlpha, needOffsetView)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT && android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {
            clearPreviousSetting(activity)
        }
    }

    /**
     * 隐藏伪状态栏 View

     * @param activity 调用的 Activity
     */
    fun hideFakeStatusBarView(activity: android.app.Activity) {
        val decorView = activity.window.decorView as android.view.ViewGroup
        val fakeStatusBarView = decorView.findViewById<View>(FAKE_STATUS_BAR_VIEW_ID)
        if (fakeStatusBarView != null) {
            fakeStatusBarView.visibility = android.view.View.GONE
        }
        val fakeTranslucentView = decorView.findViewById<View>(FAKE_TRANSLUCENT_VIEW_ID)
        if (fakeTranslucentView != null) {
            fakeTranslucentView.visibility = android.view.View.GONE
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////

    @android.annotation.TargetApi(android.os.Build.VERSION_CODES.KITKAT)
    private fun clearPreviousSetting(activity: android.app.Activity) {
        val decorView = activity.window.decorView as android.view.ViewGroup
        val fakeStatusBarView = decorView.findViewById<View>(FAKE_STATUS_BAR_VIEW_ID)
        if (fakeStatusBarView != null) {
            decorView.removeView(fakeStatusBarView)
            val rootView = (activity.findViewById<View>(android.R.id.content) as android.view.ViewGroup).getChildAt(0) as android.view.ViewGroup
            rootView.setPadding(0, 0, 0, 0)
        }
    }

    /**
     * 添加半透明矩形条

     * @param activity       需要设置的 activity
     * *
     * @param statusBarAlpha 透明值
     */
    private fun addTranslucentView(activity: android.app.Activity, @android.support.annotation.IntRange(from = 0, to = 255) statusBarAlpha: Int) {
        val contentView = activity.findViewById<View>(android.R.id.content) as android.view.ViewGroup
        val fakeTranslucentView = contentView.findViewById<View>(FAKE_TRANSLUCENT_VIEW_ID)
        if (fakeTranslucentView != null) {
            if (fakeTranslucentView.visibility == android.view.View.GONE) {
                fakeTranslucentView.visibility = android.view.View.VISIBLE
            }
            fakeTranslucentView.setBackgroundColor(android.graphics.Color.argb(statusBarAlpha, 0, 0, 0))
        } else {
            contentView.addView(createTranslucentStatusBarView(activity, statusBarAlpha))
        }
    }

    /**
     * 生成一个和状态栏大小相同的半透明矩形条

     * @param activity 需要设置的activity
     * *
     * @param color    状态栏颜色值
     * *
     * @param alpha    透明值
     * *
     * @return 状态栏矩形条
     */
    private fun createStatusBarView(activity: android.app.Activity, @android.support.annotation.ColorInt color: Int, alpha: Int = 0): android.view.View {
        // 绘制一个和状态栏一样高的矩形
        val statusBarView = android.view.View(activity)
        val params = android.widget.LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity))
        statusBarView.layoutParams = params
        statusBarView.setBackgroundColor(calculateStatusColor(color, alpha))
        statusBarView.id = FAKE_STATUS_BAR_VIEW_ID
        return statusBarView
    }

    /**
     * 设置根布局参数
     */
    private fun setRootView(activity: android.app.Activity) {
        val parent = activity.findViewById<View>(android.R.id.content) as android.view.ViewGroup
        var i = 0
        val count = parent.childCount
        while (i < count) {
            val childView = parent.getChildAt(i)
            if (childView is android.view.ViewGroup) {
                childView.setFitsSystemWindows(true)
                childView.clipToPadding = true
            }
            i++
        }
    }

    /**
     * 设置透明
     */
    private fun setTransparentForWindow(activity: android.app.Activity) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            activity.window.statusBarColor = android.graphics.Color.TRANSPARENT
            activity.window
                    .decorView.systemUiVisibility = android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE or android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        } else if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            activity.window
                    .setFlags(android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }

    /**
     * 使状态栏透明
     */
    @android.annotation.TargetApi(android.os.Build.VERSION_CODES.KITKAT)
    private fun transparentStatusBar(activity: android.app.Activity) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            activity.window.addFlags(android.view.WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            activity.window.clearFlags(android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            activity.window.addFlags(android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            activity.window.statusBarColor = android.graphics.Color.TRANSPARENT
        } else {
            activity.window.addFlags(android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }

    /**
     * 创建半透明矩形 View

     * @param alpha 透明值
     * *
     * @return 半透明 View
     */
    private fun createTranslucentStatusBarView(activity: android.app.Activity, alpha: Int): android.view.View {
        // 绘制一个和状态栏一样高的矩形
        val statusBarView = android.view.View(activity)
        val params = android.widget.LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity))
        statusBarView.layoutParams = params
        statusBarView.setBackgroundColor(android.graphics.Color.argb(alpha, 0, 0, 0))
        statusBarView.id = FAKE_TRANSLUCENT_VIEW_ID
        return statusBarView
    }

    /**
     * 获取状态栏高度

     * @param context context
     * *
     * @return 状态栏高度
     */
    private fun getStatusBarHeight(context: android.content.Context): Int {
        // 获得状态栏高度
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        return context.resources.getDimensionPixelSize(resourceId)
    }

    /**
     * 计算状态栏颜色

     * @param color color值
     * *
     * @param alpha alpha值
     * *
     * @return 最终的状态栏颜色
     */
    private fun calculateStatusColor(@android.support.annotation.ColorInt color: Int, alpha: Int): Int {
        if (alpha == 0) {
            return color
        }
        val a = 1 - alpha / 255f
        var red = color shr 16 and 0xff
        var green = color shr 8 and 0xff
        var blue = color and 0xff
        red = (red * a + 0.5).toInt()
        green = (green * a + 0.5).toInt()
        blue = (blue * a + 0.5).toInt()
        return 0xff shl 24 or (red shl 16) or (green shl 8) or blue
    }
}
