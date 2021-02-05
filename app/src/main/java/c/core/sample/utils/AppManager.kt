package c.core.sample.utils

import android.app.Activity
import android.util.Log
import java.util.*
import kotlin.system.exitProcess

/**
 * 全局app管理类
 * @author 管理员
 */
class AppManager private constructor() {

    val topActivity: Activity?
        get() = topActivity()

    /**
     * 添加Activity到堆栈
     */
    fun addActivity(activity: Activity) {
        if (activityStack == null) {
            activityStack = Stack()
        }
        activityStack.add(activity)
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    private fun topActivity(): Activity? {
        return activityStack.lastElement()
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    fun finishActivity() {
        activityStack.lastElement()?.let {
            finishActivity(it::class.java)
        }
    }

    companion object {
        private var activityStack: Stack<Activity> = Stack() // Activity栈
        private var instance: AppManager? = null // 单例模式

        /**
         * 单一实例
         */
        val appManager: AppManager
            get() {
                return instance ?: AppManager().also { instance = it }
            }

        /**
         * 结束所有Activity
         */
        fun finishAllActivity() {
            synchronized(activityStack) {
                val iterator = activityStack.iterator()
                while (iterator.hasNext()) {
                    val act = iterator.next()
                    iterator.remove()
                    act.finish()
                }
            }
        }

        fun finishActivity(cls: Class<*>) {
            synchronized(activityStack) {
                val iterator = activityStack.iterator()
                while (iterator.hasNext()) {
                    val act = iterator.next()
                    if (act.javaClass == cls) {
                        iterator.remove()
                        act.finish()
                    }
                }
            }
        }

        fun finishActivity(activity: Activity) {
            synchronized(activityStack) {
                val iterator = activityStack.iterator()
                while (iterator.hasNext()) {
                    val act = iterator.next()
                    if (act.javaClass == activity::class.java) {
                        iterator.remove()
                        act.finish()
                    }
                }
            }
        }

        fun finishActivityExclude(cls: Class<*>) {
            synchronized(activityStack) {
                val iterator = activityStack.iterator()
                while (iterator.hasNext()) {
                    val act = iterator.next()
                    if (act.javaClass != cls) {
                        iterator.remove()
                        act.finish()
                    }
                }
            }
        }

        inline fun <reified T : Any> finishActivity() {
            finishActivity(T::class.java)
        }

        inline fun <reified T : Any> finishActivityExclude() {
            finishActivityExclude(T::class.java)
        }

        /**
         * 获取指定的Activity
         * @author kymjs
         */
        fun getActivity(cls: Class<*>): Activity? {
            val iterator = activityStack.iterator()
            while (iterator.hasNext()) {
                val act = iterator.next()
                if (act.javaClass == cls) {
                    return act
                }
            }
            return null
        }

        /**
         * 退出应用程序
         */
        fun appExit() {
            try {
                finishAllActivity()
                // 杀死该应用进程
                android.os.Process.killProcess(android.os.Process.myPid())
                exitProcess(0)
            } catch (e: Exception) {
                Log.e("TAG", "appExit")
            }
        }
    }
}
