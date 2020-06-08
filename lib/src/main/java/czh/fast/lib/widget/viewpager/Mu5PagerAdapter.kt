package czh.fast.lib.widget.viewpager

import android.content.Context
import androidx.viewpager.widget.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

/**
 * Created by gjm on 2017/7/26.
 * Func:主要用于实现imageview的回调加载
 */

class Mu5PagerAdapter : androidx.viewpager.widget.PagerAdapter {

    private var mContext: Context? = null
    private var urls: List<String>? = null
    private var mu5Interface: Mu5Interface? = null

    fun Any?.toString(s:String ): String {
        if (this == null) return "null"
        return toString()
    }


    constructor(mContext: Context, urls: List<String>) {
        this.mContext = mContext
        this.urls = urls
    }

    constructor(mContext: Context, urls: List<String>, mu5Interface: Mu5Interface) {
        this.mContext = mContext
        this.urls = urls
        this.mu5Interface = mu5Interface
    }

    fun setMu5Interface(mu5Interface: Mu5Interface) {
        this.mu5Interface = mu5Interface
    }

    fun setUrls(urls: List<String>) {
        this.urls = urls
    }

    override fun getCount(): Int {
        return urls?.size ?: 0
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(mContext)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP

        mu5Interface?.onLoadImage(imageView, urls!![position], position)

        container.addView(imageView, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }
}
