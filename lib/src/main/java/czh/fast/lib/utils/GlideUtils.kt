package czh.fast.lib.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

fun ImageView.load(url: Any, resourceId: Int? = null) {
    Glide.with(context).load(url).apply(RequestOptions().apply {
        centerCrop()
        skipMemoryCache(true)
        diskCacheStrategy(DiskCacheStrategy.ALL)
        resourceId?.let {
            placeholder(it)
            error(it)
        }
    }).into(this)

}