package c.core.widget.viewpager

import android.widget.ImageView;

/**
 * Created by gjm on 2017/7/26.
 * Func:
 */

interface Mu5Interface {
    /**
     * 位置发生改变
     *
     * @param currentIndex
     */
    fun onIndexChange(currentIndex: Int)


    fun onLoadImage(imageView: ImageView, url: String, position: Int)
}
