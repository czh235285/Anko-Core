package c.core.utils

import android.view.LayoutInflater
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * 类转换初始化
 */
object TUtil {
    fun <T> getT(o: Any, i: Int): T? {
        try {
            return ((o.javaClass
                .genericSuperclass as ParameterizedType?)!!.actualTypeArguments[i] as Class<T>)
                .newInstance()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: ClassCastException) {
            e.printStackTrace()
        }
        return null
    }

    fun forName(className: String?): Class<*>? {
        try {
            return Class.forName(className!!)
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
        return null
    }
}

fun <T> LayoutInflater.getViewBinding(o: Any, i: Int): T? {
    try {

        val superclass: Type? = o.javaClass.genericSuperclass
        val method: Method =
            ((superclass as ParameterizedType?)?.actualTypeArguments?.get(i) as Class<T>).getDeclaredMethod(
                "inflate",
                LayoutInflater::class.java
            )
        return method.invoke(null,this) as T
    } catch (e: InstantiationException) {
        e.printStackTrace()
    } catch (e: IllegalAccessException) {
        e.printStackTrace()
    } catch (e: ClassCastException) {
        e.printStackTrace()
    }
    return null
}
