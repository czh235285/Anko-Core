package c.core.sample.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import c.core.sample.R


fun ImageView.load(
    url: Any?,
    placeholder: Int? = R.mipmap.ic_launcher,
    error: Int? = R.mipmap.ic_launcher
) {
    Glide.with(context).load(url)
        .centerCrop()
        .apply(
            RequestOptions().apply {
                placeholder?.let { placeholder(it) }
                error?.let { error(it) }
            }
        ).into(this)
}
