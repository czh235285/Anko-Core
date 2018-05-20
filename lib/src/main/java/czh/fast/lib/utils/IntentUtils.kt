package czh.fast.lib.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment

/**
 * Created by admin on 2018/4/27.
 */

/**
 * 跳转Class

 * @param targetClass
 */
inline fun <reified T : kotlin.Any> Activity.warpActivityForResult(requestCode: Int, bundle: Bundle? = null) {
    val intent = Intent(this, T::class.java)
    bundle?.let {
        intent.putExtras(it)
    }
    startActivityForResult(intent, requestCode)
}

/**
 * 跳转Class

 * @param targetClass
 */
inline fun <reified T : kotlin.Any> Activity.warpActivity(bundle: Bundle? = null) {
    val intent = Intent(this, T::class.java)
    bundle?.let {
        intent.putExtras(it)
    }
    startActivity(intent)
}


/**
 * 跳转Class

 * @param targetClass
 */
inline fun <reified T : kotlin.Any> Fragment.warpActivityForResult(requestCode: Int, bundle: Bundle? = null) {
    val intent = Intent(activity, T::class.java)
    bundle?.let {
        intent.putExtras(it)
    }
    startActivityForResult(intent, requestCode)
}

/**
 * 跳转Class

 * @param targetClass
 */
inline fun <reified T : kotlin.Any> Fragment.warpActivity(bundle: Bundle? = null) {
    val intent = Intent(activity, T::class.java)
    bundle?.let {
        intent.putExtras(it)
    }
    startActivity(intent)
}


/**
 * 跳转Class

 * @param targetClass
 */
inline fun <reified T : kotlin.Any> Context.warpActivity(bundle: Bundle? = null) {
    val intent = Intent(this, T::class.java)
    bundle?.let {
        intent.putExtras(it)
    }
    startActivity(intent)
}