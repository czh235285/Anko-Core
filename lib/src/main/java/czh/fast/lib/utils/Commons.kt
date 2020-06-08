package czh.fast.lib.utils

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import czh.fast.lib.widget.SimpleDividerDecoration
import java.util.regex.Pattern
import android.view.inputmethod.EditorInfo


fun View.gone() {
    this.visibility = View.GONE
}

fun kotlin.Any.gones(vararg views: View) {
    views.forEach {
        it.gone()
    }
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun kotlin.Any.invisibles(vararg views: View) {
    views.forEach {
        it.invisible()
    }
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun kotlin.Any.visibles(vararg views: View) {
    views.forEach {
        it.visible()
    }
}

fun androidx.recyclerview.widget.RecyclerView.init(layout: androidx.recyclerview.widget.RecyclerView.LayoutManager = androidx.recyclerview.widget.LinearLayoutManager(this.context)) {
    this.addItemDecoration(SimpleDividerDecoration(0xffe5e5e5.toInt(), 1))
    this.layoutManager = layout
}

fun EditText.textString(): String = this.text.toString().trim()

fun EditText.isEmpty(): Boolean = this.text.toString().trim().isEmpty()

fun EditText.isNotEmpty(): Boolean = this.text.toString().trim().isNotEmpty()

/**
 * 清除输入框数据
 */
fun EditText.clear() {
    setText("")
}

//修改光标的颜色（反射）
fun EditText.textCursorDrawable(@DrawableRes id: Int) {
    try {
        val f = TextView::class.java.getDeclaredField("mCursorDrawableRes")
        f.isAccessible = true
        f.set(this, id)
    } catch (ignored: Exception) {

    }
}


/**
 * EditText设置只能输入数字和小数点，小数点只能1个且小数点后最多只能2位
 */
fun EditText.setOnlyDecimal() {
    this.inputType = EditorInfo.TYPE_CLASS_NUMBER or EditorInfo.TYPE_NUMBER_FLAG_DECIMAL
    this.addTextChangedListener(object : TextWatcher {

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            //这部分是处理如果输入框内小数点后有俩位，那么舍弃最后一位赋值，光标移动到最后
            if (s.toString().contains(".")) {
                if (s.length - 1 - s.toString().indexOf(".") > 2) {
                    setText(s.toString().subSequence(0, s.toString().indexOf(".") + 3))
                    setSelection(s.toString().subSequence(0, s.toString().indexOf(".") + 3).length)
                }
            }

            if (s.toString().trim().substring(0) == ".") {
                setText("0$s")
                setSelection(2)
            }

            if (s.toString().startsWith("0") && s.toString().trim().length > 1) {
                if (s.toString().substring(1, 2) != ".") {
                    setText(s.subSequence(0, 1))
                    setSelection(1)
                    return
                }
            }
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int,
                                       after: Int) {
        }

        override fun afterTextChanged(s: Editable) {

        }

    })

}


fun TextView.drawableLeft(context: Context, @DrawableRes id: Int) {
    val d = context.getDrawableRes(id)
    d.setBounds(0, 0, d.minimumWidth, d.minimumHeight)
    this.setCompoundDrawables(d, null, null, null)
}

fun TextView.drawableBottom(context: Context, @DrawableRes id: Int) {
    val d = context.getDrawableRes(id)
    d.setBounds(0, 0, d.minimumWidth, d.minimumHeight)
    this.setCompoundDrawables(null, null, null, d)
}

fun TextView.drawableRight(context: Context, @DrawableRes id: Int) {
    val d = context.getDrawableRes(id)
    d.setBounds(0, 0, d.minimumWidth, d.minimumHeight)
    this.setCompoundDrawables(null, null, d, null)
}

fun TextView.drawableTop(context: Context, @DrawableRes id: Int) {
    val d = context.getDrawableRes(id)
    d.setBounds(0, 0, d.minimumWidth, d.minimumHeight)
    this.setCompoundDrawables(null, d, null, null)
}

/**
 * 验证所有EditText是否为空
 * @return 只要有空，返回false
 */
fun checkALL(vararg all: EditText): Boolean {
    all.forEach {
        if (it.isEmpty()) {
            return false
        }
    }
    return true
}

/**
 * 验证是否手机
 */
fun String.isMobile(): Boolean {
    val regex = "(\\+\\d+)?1[34578]\\d{9}$"
    return Pattern.matches(regex, this)
}

/**
 * 验证是否电话
 */
fun String.isPhone(): Boolean {
    val regex = "(\\+\\d+)?(\\d{3,4}\\-?)?\\d{7,8}$"
    return Pattern.matches(regex, this)

}

/**
 * 验证是否邮箱
 */
fun String.isEmail(): Boolean {
    val emailPattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")
    val matcher = emailPattern.matcher(this)
    if (matcher.find()) {
        return true
    }
    return false
}

/**
 * 字符串安全转换到整型，转换失败返回0
 */
fun String.safeConvertToInt(): Int {
    try {
        return toInt()
    } catch (e: Exception) {
        return 0
    }
}

/**
 * 字符串安全转换到长整型，转换失败返回0
 */
fun String.safeConvertToLong(): Long {
    try {
        return toLong()
    } catch (e: Exception) {
        return 0L
    }
}

/**
 * 字符串安全转换到双精度类型，转换失败返回0
 */
fun String.safeConvertToDouble(): Double {
    try {
        return toDouble()
    } catch (e: Exception) {
        return 0.0
    }
}

/**
 * 字符串安全转换到短整型类型，转换失败返回0
 */
fun String.safeConvertToShort(): Short {
    try {
        return toShort()
    } catch (e: Exception) {
        return 0
    }
}