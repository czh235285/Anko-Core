package c.core.utils.filter

import android.text.InputFilter
import android.text.Spanned
import android.widget.EditText
import java.util.regex.Pattern


/**
 * 身份证输入限制。前17位只能输入数字，最后一位可以是X，x
 */

fun EditText.iDCardFilter() {
    filters = arrayOf(IDCardFilter.instance, InputFilter.LengthFilter(18))
}

class IDCardFilter private constructor() : InputFilter {

    companion object {
        val instance = IDCardFilter()
    }

    private fun check(source: CharSequence?): Boolean {
        val pattern: Pattern = Pattern.compile("^[x-xX-X0-9]+\$")
        return pattern.matcher(source).matches()
    }

    private fun checkNumber(source: CharSequence?): Boolean {
        val pattern: Pattern = Pattern.compile("^[0-9]+\$")
        return pattern.matcher(source).matches()
    }

    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence {
        if (source.isNullOrEmpty()) {
            return ""
        }
        val endString = StringBuilder(dest ?: "").insert(dstart, source)

        return if (endString.length < 18) {
            if (checkNumber(endString)) {
                source
            } else {
                ""
            }
        } else {
            if (checkNumber(endString.substring(0, 17)) && check(endString)) {
                source
            } else {
                ""
            }
        }

    }
}
