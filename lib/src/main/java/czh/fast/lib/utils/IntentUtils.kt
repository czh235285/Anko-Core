package czh.fast.lib.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable

/**
 * Created by admin on 2018/5/21.
 * @author czh
 */
inline fun <reified T : kotlin.Any> Activity.warpActivityForResult(requestCode: Int, vararg params: Pair<String, Any?>) {
    val intent = Intent(this, T::class.java)
    if (params.isNotEmpty()) fillIntentArguments(intent, params)
    startActivityForResult(intent, requestCode)
}

inline fun <reified T : kotlin.Any> android.app.Fragment.warpActivityForResult(requestCode: Int, vararg params: Pair<String, Any?>) {
    val intent = Intent(activity, T::class.java)
    if (params.isNotEmpty()) fillIntentArguments(intent, params)
    startActivityForResult(intent, requestCode)
}

inline fun <reified T : kotlin.Any> android.support.v4.app.Fragment.warpActivityForResult(requestCode: Int, vararg params: Pair<String, Any?>) {
    val intent = Intent(activity, T::class.java)
    if (params.isNotEmpty()) fillIntentArguments(intent, params)
    startActivityForResult(intent, requestCode)
}

inline fun <reified T : kotlin.Any> Context.warpActivity(vararg params: Pair<String, Any?>) {
    val intent = Intent(this, T::class.java)
    if (params.isNotEmpty()) fillIntentArguments(intent, params)
    startActivity(intent)
}

inline fun <reified T : kotlin.Any> Activity.warpActivity(vararg params: Pair<String, Any?>) {
    val intent = Intent(this, T::class.java)
    if (params.isNotEmpty()) fillIntentArguments(intent, params)
    startActivity(intent)
}

inline fun <reified T : kotlin.Any> android.support.v4.app.Fragment.warpActivity(vararg params: Pair<String, Any?>) {
    val intent = Intent(activity, T::class.java)
    if (params.isNotEmpty()) fillIntentArguments(intent, params)
    startActivity(intent)
}

inline fun <reified T : kotlin.Any> android.app.Fragment.warpActivity(vararg params: Pair<String, Any?>) {
    val intent = Intent(activity, T::class.java)
    if (params.isNotEmpty()) fillIntentArguments(intent, params)
    startActivity(intent)
}

fun fillIntentArguments(intent: Intent, params: Array<out Pair<String, Any?>>) {
    params.forEach {
        val value = it.second
        when (value) {
            null -> intent.putExtra(it.first, null as Serializable?)
            is Int -> intent.putExtra(it.first, value)
            is Long -> intent.putExtra(it.first, value)
            is CharSequence -> intent.putExtra(it.first, value)
            is String -> intent.putExtra(it.first, value)
            is Float -> intent.putExtra(it.first, value)
            is Double -> intent.putExtra(it.first, value)
            is Char -> intent.putExtra(it.first, value)
            is Short -> intent.putExtra(it.first, value)
            is Boolean -> intent.putExtra(it.first, value)
            is Serializable -> intent.putExtra(it.first, value)
            is Bundle -> intent.putExtra(it.first, value)
            is Parcelable -> intent.putExtra(it.first, value)
            is Array<*> -> when {
                value.isArrayOf<CharSequence>() -> intent.putExtra(it.first, value)
                value.isArrayOf<String>() -> intent.putExtra(it.first, value)
                value.isArrayOf<Parcelable>() -> intent.putExtra(it.first, value)
                else -> throw Exception("Intent extra ${it.first} has wrong type ${value.javaClass.name}")
            }
            is IntArray -> intent.putExtra(it.first, value)
            is LongArray -> intent.putExtra(it.first, value)
            is FloatArray -> intent.putExtra(it.first, value)
            is DoubleArray -> intent.putExtra(it.first, value)
            is CharArray -> intent.putExtra(it.first, value)
            is ShortArray -> intent.putExtra(it.first, value)
            is BooleanArray -> intent.putExtra(it.first, value)
            else -> throw Exception("Intent extra ${it.first} has wrong type ${value.javaClass.name}")
        }
        return@forEach
    }
}