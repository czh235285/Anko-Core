package c.core.ex

import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.widget.TextView
import androidx.annotation.DrawableRes
import c.core.utils.resDrawable

fun TextView.pSize(size: Int) {
    setTextSize(TypedValue.COMPLEX_UNIT_PX, size.pxf)
}

fun TextView.bold(isBold: Boolean = true) {
    typeface = if (isBold) {
        Typeface.defaultFromStyle(Typeface.BOLD)
    } else {
        Typeface.defaultFromStyle(Typeface.NORMAL)
    }
}

fun TextView.leftDrawable(
    @DrawableRes id: Int,
    width: Int? = null,
    height: Int? = null,
    compoundDrawablePadding: Int? = null
) {
    context.resDrawable(id)?.also { d ->
        d.setBounds(0, 0, width?.px ?: d.minimumWidth, height?.px ?: minimumHeight)
        compoundDrawablePadding?.let {
            this.compoundDrawablePadding = it.px
        }
        this.setCompoundDrawables(d, null, null, null)
    }
}

fun TextView.topDrawable(
    @DrawableRes id: Int,
    width: Int? = null,
    height: Int? = null,
    compoundDrawablePadding: Int? = null
) {
    context.resDrawable(id)?.also { d ->
        d.setBounds(0, 0, width?.px ?: d.minimumWidth, height?.px ?: minimumHeight)
        compoundDrawablePadding?.let {
            this.compoundDrawablePadding = it.px
        }
        this.setCompoundDrawables(null, d, null, null)
    }
}

fun TextView.rightDrawable(
    @DrawableRes id: Int,
    width: Int? = null,
    height: Int? = null,
    compoundDrawablePadding: Int? = null
) {
    context.resDrawable(id)?.also { d ->
        d.setBounds(0, 0, width?.px ?: d.minimumWidth, height?.px ?: minimumHeight)
        compoundDrawablePadding?.let {
            this.compoundDrawablePadding = it.px
        }
        this.setCompoundDrawables(null, null, d, null)
    }
}

fun TextView.bottomDrawable(
    @DrawableRes id: Int,
    width: Int? = null,
    height: Int? = null,
    compoundDrawablePadding: Int? = null
) {
    context.resDrawable(id)?.also { d ->
        d.setBounds(0, 0, width?.px ?: d.minimumWidth, height?.px ?: minimumHeight)
        compoundDrawablePadding?.let {
            this.compoundDrawablePadding = it.px
        }
        this.setCompoundDrawables(null, null, null, d)
    }
}

fun TextView.textDrawable(init: TextDrawable.() -> Unit) {
    TextDrawable().also {
        it.init()
        this.setCompoundDrawables(
            it.leftDrawable,
            it.topDrawable,
            it.rightDrawable,
            it.bottomDrawable
        )
    }
}

fun TextView.buildDrawable(init: CreateDrawable.() -> Unit): Drawable? {
    val data = CreateDrawable().apply(init)
    if (data.id == null) return null
    return context.resDrawable(data.id!!)?.also { d ->
        d.setBounds(0, 0, data.width?.px ?: d.minimumWidth, data.height?.px ?: d.minimumHeight)
        data.padding?.let {
            this.compoundDrawablePadding = it.px
        }
    }
}

class TextDrawable {
    internal var topDrawable: Drawable? = null
    internal var leftDrawable: Drawable? = null
    internal var bottomDrawable: Drawable? = null
    internal var rightDrawable: Drawable? = null

    fun TextView.top(init: CreateDrawable.() -> Unit) {
        val data = CreateDrawable().apply(init)
        if (data.id == null) return
        topDrawable = context.resDrawable(data.id!!)?.also { d ->
            d.setBounds(0, 0, data.width?.px ?: d.minimumWidth, data.height?.px ?: d.minimumHeight)
            data.padding?.let {
                this.compoundDrawablePadding = it.px
            }
        }
    }

    fun TextView.left(init: CreateDrawable.() -> Unit) {
        val data = CreateDrawable().apply(init)
        if (data.id == null) return
        leftDrawable = context.resDrawable(data.id!!)?.also { d ->
            d.setBounds(0, 0, data.width?.px ?: d.minimumWidth, data.height?.px ?: d.minimumHeight)
            data.padding?.let {
                this.compoundDrawablePadding = it.px
            }
        }
    }

    fun TextView.bottom(init: CreateDrawable.() -> Unit) {
        val data = CreateDrawable().apply(init)
        if (data.id == null) return
        bottomDrawable = context.resDrawable(data.id!!)?.also { d ->
            d.setBounds(0, 0, data.width?.px ?: d.minimumWidth, data.height?.px ?: d.minimumHeight)
            data.padding?.let {
                this.compoundDrawablePadding = it.px
            }
        }
    }

    fun TextView.right(init: CreateDrawable.() -> Unit) {
        val data = CreateDrawable().apply(init)
        if (data.id == null) return
        rightDrawable = context.resDrawable(data.id!!)?.also { d ->
            d.setBounds(0, 0, data.width?.px ?: d.minimumWidth, data.height?.px ?: d.minimumHeight)
            data.padding?.let {
                this.compoundDrawablePadding = it.px
            }
        }
    }
}

class CreateDrawable {
    @DrawableRes
    var id: Int? = null
    var width: Int? = null
    var height: Int? = null
    var padding: Int? = null
}
