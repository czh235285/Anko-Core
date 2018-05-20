package czh.fast.lib.utils

import android.content.Context
import android.widget.ImageView
import com.youth.banner.loader.ImageLoader

/**
 * Created by Administrator on 2017/3/3.
 */

class GlideImageLoader : ImageLoader() {
    override fun displayImage(context: Context, path: Any, imageView: ImageView) {
        //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
        imageView.load(path)
    }
}
