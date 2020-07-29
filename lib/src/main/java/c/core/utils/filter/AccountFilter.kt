package c.core.utils.filter

import android.text.InputFilter
import android.text.Spanned
import java.util.regex.Matcher
import java.util.regex.Pattern

class AccountFilter : InputFilter {

    private fun check(source: CharSequence?): Boolean {
        val pattern: Pattern = Pattern.compile("^[A-Za-z0-9]+\$")
        val matcher: Matcher = pattern.matcher(source ?: "")
        return matcher.matches()
    }

    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence {
        return if (check(source)) {
            source ?: ""
        } else {
            ""
        }
    }
}