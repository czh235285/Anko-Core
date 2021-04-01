package c.core.utils

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.fragment.app.Fragment
import java.io.Serializable

/**
 * Created by admin on 2018/5/21.
 * @author czh
 */
inline fun <reified T : kotlin.Any> Activity.openActivityForResult(
    requestCode: Int,
    vararg params: Pair<String, Any?>
) {
    val intent = Intent(this, T::class.java)
    if (params.isNotEmpty()) fillIntentArguments(intent, params)
    startActivityForResult(intent, requestCode)
}

inline fun <reified T : kotlin.Any> Fragment.openActivityForResult(
    requestCode: Int,
    vararg params: Pair<String, Any?>
) {
    val intent = Intent(activity, T::class.java)
    if (params.isNotEmpty()) fillIntentArguments(intent, params)
    startActivityForResult(intent, requestCode)
}

inline fun <reified T : kotlin.Any> Context.openActivity(vararg params: Pair<String, Any?>) {
    val intent = Intent(this, T::class.java)
    if (params.isNotEmpty()) fillIntentArguments(intent, params)
    startActivity(intent)
}

inline fun <reified T : kotlin.Any> Context.openActivityAndClearTask(vararg params: Pair<String, Any?>) {
    val intent = Intent(this, T::class.java)
    if (params.isNotEmpty()) fillIntentArguments(intent, params)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    startActivity(intent)
}

inline fun <reified T : kotlin.Any> Activity.openActivity(vararg params: Pair<String, Any?>) {
    val intent = Intent(this, T::class.java)
    if (params.isNotEmpty()) fillIntentArguments(intent, params)
    startActivity(intent)
}

inline fun <reified T : kotlin.Any> Fragment.openActivity(vararg params: Pair<String, Any?>) {
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
inline fun <reified T : kotlin.Any> Context.openActivityByTransition(
    view: View? = null, string: String = "", vararg params: Pair<String,
            Any?>
) {
    if (Build.VERSION.SDK_INT >= 21) {
        val intent = Intent(this, T::class.java)
        val bundle = if (view == null) {
            ActivityOptions.makeSceneTransitionAnimation(this as Activity).toBundle()
        } else {
            ActivityOptions
                .makeSceneTransitionAnimation(
                    this as Activity,
                    android.util.Pair.create(view, string)
                )
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

fun Activity.optString(key: String, default: String = ""): String {
    return optIntentParams(key, default) ?: ""
}

fun Activity.optInt(key: String, default: Int? = null): Int? {
    return optIntentParams(key, default)
}

fun Activity.optBoolean(key: String, default: Boolean? = null): Boolean? {
    return optIntentParams(key, default)
}

@Suppress("IMPLICIT_CAST_TO_ANY")
inline fun <reified T : Any?> Activity.optIntentParams(key: String, def: T? = null): T? {

    if (intent.hasExtra(key)) {
        val res = when {
            Boolean::class.java.isAssignableFrom(T::class.java) ->
                intent.getBooleanExtra(key, (def as? Boolean) ?: false)
            Byte::class.java.isAssignableFrom(T::class.java) ->
                intent.getByteExtra(key, (def as? Byte) ?: 0.toByte())
            Char::class.java.isAssignableFrom(T::class.java) ->
                intent.getCharExtra(key, (def as? Char) ?: 0.toChar())
            Short::class.java.isAssignableFrom(T::class.java) ->
                intent.getShortExtra(key, (def as? Short) ?: 0.toShort())
            Int::class.java.isAssignableFrom(T::class.java) ->
                intent.getIntExtra(key, (def as? Int) ?: 0)
            Long::class.java.isAssignableFrom(T::class.java) ->
                intent.getLongExtra(key, (def as? Long) ?: 0L)
            Float::class.java.isAssignableFrom(T::class.java) ->
                intent.getFloatExtra(key, (def as? Float) ?: 0F)
            Double::class.java.isAssignableFrom(T::class.java) ->
                intent.getDoubleExtra(key, (def as? Double) ?: 0.0)

            Bundle::class.java.isAssignableFrom(T::class.java) -> intent.getBundleExtra(key)
            CharSequence::class.java.isAssignableFrom(T::class.java) -> intent.getCharSequenceExtra(
                key
            )
            String::class.java.isAssignableFrom(T::class.java) -> intent.getStringExtra(key)
            Parcelable::class.java.isAssignableFrom(T::class.java) -> intent.getParcelableExtra(key)
            Serializable::class.java.isAssignableFrom(T::class.java) -> intent.getSerializableExtra(
                key
            )

            IntArray::class.java.isAssignableFrom(T::class.java) -> intent.getIntArrayExtra(key)
            LongArray::class.java.isAssignableFrom(T::class.java) -> intent.getLongArrayExtra(key)
            FloatArray::class.java.isAssignableFrom(T::class.java) -> intent.getFloatArrayExtra(key)
            DoubleArray::class.java.isAssignableFrom(T::class.java) -> intent.getDoubleArrayExtra(
                key
            )
            CharArray::class.java.isAssignableFrom(T::class.java) -> intent.getCharArrayExtra(key)
            ShortArray::class.java.isAssignableFrom(T::class.java) -> intent.getShortArrayExtra(key)
            BooleanArray::class.java.isAssignableFrom(T::class.java) -> intent.getBooleanArrayExtra(
                key
            )
            ByteArray::class.java.isAssignableFrom(T::class.java) -> intent.getByteArrayExtra(key)

            else -> throw IllegalArgumentException("$key-> type <T> :${T::class.java.simpleName} not support")
        }
        return res as? T ?: def
    }
    return def
}
