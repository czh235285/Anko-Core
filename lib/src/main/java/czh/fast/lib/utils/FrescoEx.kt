package czh.fast.lib.utils

import android.graphics.drawable.Animatable
import android.net.Uri
import android.view.ViewGroup
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.controller.BaseControllerListener
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.common.ResizeOptions
import com.facebook.imagepipeline.image.ImageInfo
import com.facebook.imagepipeline.request.ImageRequestBuilder

fun SimpleDraweeView.load(url: String) {
    val uri = Uri.parse(url)
    setImageURI(uri)
}

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

fun SimpleDraweeView.wrapHeight(url: String) {

    val vp = layoutParams as ViewGroup.LayoutParams
    val controllerListener = object : BaseControllerListener<ImageInfo>() {
        override fun onFinalImageSet(id: String?, imageInfo: ImageInfo?, animatable: Animatable?) {
            super.onFinalImageSet(id, imageInfo, animatable)
            imageInfo?.let {
                vp.width = this@wrapHeight.width
                vp.height = ((this@wrapHeight.width * it.height).toFloat() / it.width.toFloat()).toInt()
                layoutParams = vp
                requestLayout()
            }
        }
    }

    val controller = Fresco.newDraweeControllerBuilder().setControllerListener(controllerListener).setUri(Uri.parse(url)).build()
    setController(controller)
}