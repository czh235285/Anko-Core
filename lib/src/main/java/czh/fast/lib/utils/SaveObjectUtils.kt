package czh.fast.lib.utils

/**
 * Created by Administrator on 2017/4/14.
 */

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import android.util.Log

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.StreamCorruptedException


class SaveObjectUtils(private val context: Context?, private val name: String) {

    /**
     * 根据key和预期的value类型获取value的值

     * @param key
     * *
     * @param clazz
     * *
     * @return
     */
    fun <T> getValue(key: String, clazz: Class<T>): T? {
        if (context == null) {
            throw RuntimeException("请先调用带有context，name参数的构造！")
        }
        val sp = this.context.getSharedPreferences(this.name, Context.MODE_PRIVATE)
        return getValue(key, clazz, sp)
    }

    /**
     * 针对复杂类型存储<对象>

     * @param key
     * *
     * @param object
    </对象> */
    fun setObject(key: String, `object`: Any) {
        val sp = this.context!!.getSharedPreferences(this.name, Context.MODE_PRIVATE)

        //创建字节输出流
        val baos = ByteArrayOutputStream()
        //创建字节对象输出流
        var out: ObjectOutputStream? = null
        try {
            //然后通过将字对象进行64转码，写入key值为key的sp中
            out = ObjectOutputStream(baos)
            out.writeObject(`object`)
            val objectVal = String(Base64.encode(baos.toByteArray(), Base64.DEFAULT))
            val editor = sp.edit()
            editor.putString(key, objectVal)
            editor.commit()

        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                baos?.close()
                if (out != null) {
                    out.close()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }

    fun <T> getObject(key: String, clazz: Class<T>): T? {
        val sp = this.context!!.getSharedPreferences(this.name, Context.MODE_PRIVATE)
        if (sp.contains(key)) {
            val objectVal = sp.getString(key, null)
            val buffer = Base64.decode(objectVal, Base64.DEFAULT)
            //一样通过读取字节流，创建字节流输入流，写入对象并作强制转换
            val bais = ByteArrayInputStream(buffer)
            var ois: ObjectInputStream? = null
            try {
                ois = ObjectInputStream(bais)
                val t = ois.readObject() as T
                return t
            } catch (e: StreamCorruptedException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            } finally {
                try {
                    bais?.close()
                    if (ois != null) {
                        ois.close()
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }
        return null
    }

    /**
     * 对于外部不可见的过渡方法

     * @param key
     * *
     * @param clazz
     * *
     * @param sp
     * *
     * @return
     */
    private fun <T> getValue(key: String, clazz: Class<T>, sp: SharedPreferences): T? {
        val t: T
        try {

            t = clazz.newInstance()

            if (t is Int) {
                return Integer.valueOf(sp.getInt(key, 0)) as T
            } else if (t is String) {
                return sp.getString(key, "") as T
            } else if (t is Boolean) {
                return java.lang.Boolean.valueOf(sp.getBoolean(key, false)) as T
            } else if (t is Long) {
                return java.lang.Long.valueOf(sp.getLong(key, 0L)) as T
            } else if (t is Float) {
                return java.lang.Float.valueOf(sp.getFloat(key, 0f)) as T
            }
        } catch (e: InstantiationException) {
            e.printStackTrace()
            Log.e("system", "类型输入错误或者复杂类型无法解析[" + e.message + "]")
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
            Log.e("system", "类型输入错误或者复杂类型无法解析[" + e.message + "]")
        }

        Log.e("system", "无法找到" + key + "对应的值")
        return null
    }


}