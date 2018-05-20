package czh.fast.lib.utils

import android.content.Context
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun EditText.TextContent(): String = this.text.toString().trim()
fun EditText.isEmpty(): Boolean = this.text.toString().trim().isEmpty()
fun EditText.isNotEmpty(): Boolean = this.text.toString().trim().isNotEmpty()

fun Context.toast(message: CharSequence) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.getInflaterView(@LayoutRes resource: Int): View = LayoutInflater.from(this).inflate(resource, null)
fun checkALL(all: Array<EditText>): Boolean {
    all.forEach {
        if (it.isEmpty()) {
            return false
        }
    }
    return true
}

fun ImageView.load(url: Any, resourceId: Int? = null) {
    Glide.with(context).load(url).apply(RequestOptions().apply {
        centerCrop()
        resourceId?.let {
            placeholder(it)
            error(it)
        }
    }).into(this)

}