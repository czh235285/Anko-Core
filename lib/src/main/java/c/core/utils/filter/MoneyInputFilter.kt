package c.core.utils.filter

import android.text.InputFilter
import android.text.Spanned

// 限制小数点后面默认只能有两位小数
class MoneyInputFilter(private var pointLength: Int = 2) : InputFilter {
    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int
    ): CharSequence {
        val startInput = dest.subSequence(0, dstart)
        val endInput = dest.subSequence(dend, dest.length)
        val target = startInput.toString() + source + endInput // 字符串变化后的结果
        val backup = dest.subSequence(dstart, dend) // 将要被替换的字符串

        if (target.indexOf(".") == 0) { // 不允许第一个字符为.
            return backup
        }

        if (target.startsWith("0") && !target.startsWith("0.") && "0" != target) {//不允许出现0123、0456这类字符串
            return backup
        }

        val index = target.indexOf(".")
        if (index >= 0 && index + pointLength + 2 <= target.length) {
            return backup
        }

        return source
    }
}
