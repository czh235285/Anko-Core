package czh.fast.lib.utils

import android.net.Uri
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.controller.BaseControllerListener
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.common.ResizeOptions
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