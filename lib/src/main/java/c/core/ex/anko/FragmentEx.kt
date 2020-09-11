package c.core.ex.anko

import android.os.Bundle
import androidx.fragment.app.Fragment


/**
 * 扩展fragment实例化传递数据
 * 只扩展了几种基础数据类型，基本够用
 * fragment和act共享数据也可以通过viewModel去实现，fragment传递数据只传position就行
 */
inline fun <reified F : Fragment> F.addArgs(vararg args: Pair<String, Any>): F {
    return this.apply {
        arguments = Bundle().apply {
            args.forEach {
                when (val value = it.second) {
                    is String -> putString(it.first, value)
                    is Int -> putInt(it.first, value)
                    is Long -> putLong(it.first, value)
                    is Float -> putFloat(it.first, value)
                    is Double -> putDouble(it.first, value)
                    is Char -> putChar(it.first, value)
                    is Short -> putShort(it.first, value)
                    is Boolean -> putBoolean(it.first, value)
                    is Byte -> putByte(it.first, value)
                }
            }
        }
    }
}

inline fun <reified T> Fragment.opt(key: String): T? {
    return arguments?.get(key) as? T
}