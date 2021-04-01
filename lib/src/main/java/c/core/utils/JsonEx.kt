package c.core.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.Exception


val gson: Gson = Gson()

/**
 * json 字符串转对象
 */
inline fun <reified T> String?.toObj(): T? = try {
    gson.fromJson(this, object : TypeToken<T>() {}.type)
} catch (e: Throwable) {
    null
}

/**
 * 任意对象转json字符串
 */
val Any.jsonStr: String
    get() = try {
        gson.toJson(this)
    } catch (e: Exception) {
        ""
    }
