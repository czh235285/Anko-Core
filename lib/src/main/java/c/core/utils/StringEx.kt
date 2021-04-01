package c.core.utils

import java.util.regex.Pattern

/**
 * 去掉所有html标签返回文字
 *
 * @param htmlStr
 * @return
 */
fun String?.delHTMLTag(): String? {
    if (this == null) return ""
    var htmlStr = this
    val regexScript = "<script[^>]*?>[\\s\\S]*?<\\/script>" // 定义script的正则表达式
    val regexStyle = "<style[^>]*?>[\\s\\S]*?<\\/style>" // 定义style的正则表达式
    val regexHtml = "<[^>]+>" // 定义HTML标签的正则表达式

    val pScript = Pattern.compile(regexScript, Pattern.CASE_INSENSITIVE)
    val mScript = pScript.matcher(htmlStr)
    htmlStr = mScript.replaceAll("") // 过滤script标签

    val pStyle = Pattern.compile(regexStyle, Pattern.CASE_INSENSITIVE)
    val mStyle = pStyle.matcher(htmlStr ?: "")
    htmlStr = mStyle.replaceAll("") // 过滤style标签

    val pHtml = Pattern.compile(regexHtml, Pattern.CASE_INSENSITIVE)
    val mHtml = pHtml.matcher(htmlStr ?: "")
    htmlStr = mHtml.replaceAll("") // 过滤html标签

    return htmlStr.trim { it <= ' ' } // 返回文本字符串
}
