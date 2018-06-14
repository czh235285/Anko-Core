package czh.fast.lib.utils

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.view.View
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
inline fun <reified T : kotlin.Any> Context.warpActivityAndClearTask(vararg params: Pair<String, Any?>) {
    val intent = Intent(this, T::class.java)
    if (params.isNotEmpty()) fillIntentArguments(intent, params)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
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


/**
 * 共享元素转场、分解、滑动进入、淡入淡出
 * @param view     共享元素
 * @param string   共享元素添加相同的android:transitionName=""
 * @author czh
 */
inline fun <reified T : kotlin.Any> Context.warpActivityByTransition(view: View? = null, string: String = "", vararg params: Pair<String,
        Any?>) {
    if (Build.VERSION.SDK_INT >= 21) {
        val intent = Intent(this, T::class.java)
        val bundle = if (view == null) {
            ActivityOptions.makeSceneTransitionAnimation(this as Activity).toBundle()
        } else {
            ActivityOptions
                    .makeSceneTransitionAnimation(this as Activity, android.util.Pair.create(view, string))
                    .toBundle()
        }
        if (params.isNotEmpty()) fillIntentArguments(intent, params)
        startActivity(intent, bundle)
    } else {
        val intent = Intent(this, T::class.java)
        if (params.isNotEmpty()) fillIntentArguments(intent, params)
        startActivity(intent)
    }
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