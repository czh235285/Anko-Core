package c.core.utils.span

import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.view.View
import android.widget.TextView
import androidx.core.text.inSpans
import c.core.ex.appCtx
import c.core.utils.resDrawable


fun TextView.textSpan(init: SpanEx.() -> Unit) {
    SpanEx().also {
        it.tv = this
        it.init()
        text = it.mBuilder

    }
}

class SpanEx {
    internal val mBuilder: SpannableStringBuilder = SpannableStringBuilder()
    internal var tv: TextView? = null

    fun bold(text: CharSequence) {
        mBuilder.inSpans(StyleSpan(Typeface.BOLD)) {
            mBuilder.append(text)
        }
    }

    fun italic(text: CharSequence) {
        mBuilder.inSpans(StyleSpan(Typeface.ITALIC)) {
            mBuilder.append(text)
        }
    }

    fun boldItalic(text: CharSequence) {
        mBuilder.inSpans(StyleSpan(Typeface.BOLD_ITALIC)) {
            mBuilder.append(text)
        }
    }

    fun space(space: Int) {
        mBuilder.inSpans(SpaceSpan(space)) {
            mBuilder.append("text")
        }
    }

    fun underline(text: CharSequence) {
        mBuilder.inSpans(UnderlineSpan()) {
            mBuilder.append(text)
        }
    }

    fun strikeThrough(text: CharSequence) {
        mBuilder.inSpans(StrikethroughSpan()) {
            mBuilder.append(text)
        }
    }

    fun relativeSize(text: CharSequence, proportion: Float) {
        mBuilder.inSpans(RelativeSizeSpan(proportion)) {
            mBuilder.append(text)
        }
    }

    fun absoluteSize(text: CharSequence, size: Int) {
        mBuilder.inSpans(AbsoluteSizeSpan(size)) {
            mBuilder.append(text)
        }
    }

    fun clickSpan(action: () -> Unit): ClickableSpan {
        val span = object : ClickableSpan() {
            override fun onClick(widget: View) {
                action.invoke()
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = false
            }
        }
        tv?.movementMethod = LinkMovementMethod.getInstance()
        return span
    }

    fun click(
        text: CharSequence,
        textColor: Int? = null,
        isBold: Boolean = false,
        action: () -> Unit
    ) {
        mBuilder.inSpans(object : ClickableSpan() {
            override fun onClick(widget: View) {
                action.invoke()
            }

            override fun updateDrawState(ds: TextPaint) {
                textColor?.let {
                    ds.color = textColor
                }

                if (isBold) {
                    ds.typeface = Typeface.DEFAULT_BOLD
                }
                ds.isUnderlineText = false//去掉下划线
            }
        }) {
            mBuilder.append(text)
        }
        tv?.movementMethod = LinkMovementMethod.getInstance()
    }

    fun spans(text: CharSequence, vararg spans: Any) {
        val textLength = text.length
        mBuilder.append(text)
        spans.forEach { span ->
            mBuilder.setSpan(
                span,
                mBuilder.length - textLength,
                mBuilder.length,
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE
            )
        }
    }

    fun fontFamily(text: CharSequence, fontFamily: String) {
        mBuilder.inSpans(TypefaceSpan(fontFamily)) {
            mBuilder.append(text)
        }
    }

    fun foregroundColor(text: CharSequence, color: Int) {
        mBuilder.inSpans(ForegroundColorSpan(color)) {
            mBuilder.append(text)
        }
    }

    fun backgroundColorSpan(text: CharSequence, color: Int) {
        mBuilder.inSpans(BackgroundColorSpan(color)) {
            mBuilder.append(text)
        }
    }

    fun image(res: Int, width: Int? = null, height: Int? = null) {
        appCtx.resDrawable(res)?.also {
            it.setBounds(0, 0, width ?: it.minimumWidth, height ?: it.minimumHeight)
        }?.let {
            mBuilder.inSpans(VerticalImageSpan(it)) {
                mBuilder.append("text")
            }
        }
    }
}



