package c.core.utils.filter

import android.text.InputFilter
import android.text.Spanned
import java.util.regex.Pattern

/**
 * 限制只能输入中英文
 */
class RealNameFilter : InputFilter {

    private fun check(source: CharSequence?): Boolean {
        val pattern: Pattern = Pattern.compile("^[A-Za-z]+\$")
        val chinesePattern: Pattern = Pattern.compile("^[\\u4e00-\\u9fa5]+\$")
        return pattern.matcher(source).matches() || chinesePattern.matcher(source).matches()
    }

    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence {
        if (source == null) {
            return ""
        }
        return if (check(source)) {
            source
        } else {
            ""
        }
    }
}
