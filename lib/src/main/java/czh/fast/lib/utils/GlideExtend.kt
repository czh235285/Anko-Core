package czh.fast.lib.utils

import android.support.annotation.DrawableRes
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun ImageView.load(url: Any, @DrawableRes resourceId: Int? = null) {
    Glide.with(context)
            .load(url)
            .apply {
                diskCacheStrategy(DiskCacheStrategy.SOURCE)
                resourceId?.let {
                    placeholder(it)
                    error(it)
                }
                thumbnail(0.2f)
                dontAnimate()
            }.into(this)

}