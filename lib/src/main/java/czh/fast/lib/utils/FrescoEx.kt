package czh.fast.lib.utils

import android.graphics.drawable.Animatable
import android.net.Uri
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.controller.BaseControllerListener
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.common.ResizeOptions
import com.facebook.imagepipeline.image.ImageInfo
import com.facebook.imagepipeline.request.ImageRequestBuilder

/**
 * @param url 图片地址
 */
fun SimpleDraweeView.load(url: String) {
    val uri = Uri.parse(url)
    setImageURI(uri)
}

/**
 * @param url 图片地址
 * @param width Resize宽度
 * @param height Resize高度
 */
fun SimpleDraweeView.load(url: String, width: Int, height: Int) {
    val request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url))
            .setResizeOptions(ResizeOptions(width, height))
            .build()
    controller = Fresco.newDraweeControllerBuilder()
            .setImageRequest(request)
            .setOldController(controller)
            .setControllerListener(BaseControllerListener())
            .build()
}

/**
 * SimpleDraweeView高度自适应
 */
fun SimpleDraweeView.loadWrapHeight(url: String) {
    controller = Fresco.newDraweeControllerBuilder()
            .setControllerListener(object : BaseControllerListener<ImageInfo>() {
                override fun onFinalImageSet(id: String?, imageInfo: ImageInfo?, animatable: Animatable?) {
                    super.onFinalImageSet(id, imageInfo, animatable)
                    imageInfo?.let {
                        layoutParams.height = ((this@loadWrapHeight.width * it.height).toFloat() / it.width.toFloat()).toInt()
                    }
                }
            }).setUri(Uri.parse(url)).build()
}