import android.app.*
import android.view.*
import android.widget.*
import org.jetbrains.anko.*
import android.os.Bundle

class SomeActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super<Activity>.onCreate(savedInstanceState)

        android.support.design.widget.CoordinatorLayout {
            android.support.design.widget.AppBarLayout {
                fitsSystemWindows = "true"
                id = Ids.appbar
                app:theme = @style/ToolbarTheme
            
                android.support.design.widget.CollapsingToolbarLayout {
                    id = Ids.collapsingToolbar
                    app:contentScrim = @color/colorPrimary
                    app:layout_scrollFlags = scroll|exitUntilCollapsed
                    app:title = 返回
                
                    linearLayout {
                        orientation = LinearLayout.VERTICAL
                        scaleType = "centerInside"
                        app:layout_collapseMode = parallax
                    
                        com.youth.banner.Banner {
                            id = Ids.banner
                        }.lparams(width = matchParent, height = dip(275))
                    }.lparams(width = matchParent, height = wrapContent)
                    android.support.v7.widget.Toolbar {
                        backgroundColor = 0x00ffffff.toInt()
                        id = Ids.toolbar
                        app:layout_collapseMode = pin
                        app:navigationIcon = @mipmap/back
                        app:popupTheme = @style/AppTheme.PopupOverlay
                        app:theme = @style/ToolbarTheme
                        app:titleTextColor = #ffffff
                    }.lparams(width = matchParent, height = ?attr/actionBarSize)
                }.lparams(width = matchParent, height = matchParent)
                android.support.design.widget.TabLayout {
                    backgroundResource = R.drawable.selector_list_item
                    id = Ids.tabLayout
                    app:tabIndicatorColor = #666666
                    app:tabSelectedTextColor = #4D4D4D
                    app:tabTextColor = #A7A7A7
                }.lparams(width = matchParent, height = wrapContent)
            }.lparams(width = matchParent, height = wrapContent)
            android.support.v4.view.ViewPager {
                id = Ids.viewpager
                app:layout_behavior = @string/appbar_scrolling_view_behavior
            }.lparams(width = matchParent, height = wrapContent)
        }
    }

    private object Ids {
        val appbar = 1
        val banner = 2
        val collapsingToolbar = 3
        val tabLayout = 4
        val toolbar = 5
        val viewpager = 6
    }
}